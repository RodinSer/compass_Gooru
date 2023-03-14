package com.example.gooru.feature.presentation.authorization.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.extensions.emailValidation
import com.example.gooru.core.provide.AuthTokenProvider
import com.example.gooru.feature.domain.useCase.auth.AuthUseCase
import com.example.gooru.feature.domain.useCase.auth.ResetPasswordUseCase
import com.example.gooru.feature.presentation.authorization.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val tokenProvider: AuthTokenProvider,
    private val dispatchers: DispatchersWrapper,
) : BaseViewModel() {

    override fun errorHandler(error: Throwable) {
        if (error.message == "HTTP 400 Bad Request")
            _stateAuth.value = AuthState.ERROR_400
        else _stateAuth.value = AuthState.ERROR_WI_FI
    }

    private var isPasswordValidator = false
    private var isEmailValidator = false

    private val _stateAuth = MutableStateFlow(AuthState.STARTED)
    val stateAuth = _stateAuth.asStateFlow()

    fun getToken(email: String, password: String) =
        viewModelScope.launch(dispatchers.io+handler) {
             if (tokenProvider.tokenContain()) tokenProvider.clearToken()
            _stateAuth.value = AuthState.LOADING
            tokenProvider.putToken(authUseCase.getToken(email, password).authToken)
            _stateAuth.value = AuthState.SUCCESS_AUTH
        }

    fun checkToken() {
        if (tokenProvider.tokenContain()) _stateAuth.value = AuthState.SUCCESS_AUTH
    }

    fun resetPassword(email: String) {
        viewModelScope.launch(dispatchers.io) {
            _stateAuth.value = AuthState.LOADING
            resetPasswordUseCase.resetPassword(email)
            _stateAuth.value = AuthState.SUCCESS_RESET
        }
    }

    private fun enableButton() {
        Log.e("Kart","$isEmailValidator $isPasswordValidator")
        if (isEmailValidator && isPasswordValidator)
            _stateAuth.value = AuthState.ENABLED_BUTTON
        else _stateAuth.value = AuthState.STARTED
    }

    fun emailValidation(email: String) {
        isEmailValidator = email.emailValidation()
        enableButton()
    }

    fun passwordValidation(password: String) {
        isPasswordValidator = password.length >= 8
        enableButton()
    }
}