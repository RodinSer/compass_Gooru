package com.example.gooru.feature.presentation.authorization.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.extensions.emailValidation
import com.example.gooru.core.provide.AuthTokenProvider
import com.example.gooru.feature.domain.useCase.auth.AuthUseCase
import com.example.gooru.feature.domain.useCase.auth.ResetPasswordUseCase
import com.example.gooru.core.states.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val tokenProvider: AuthTokenProvider,
    private val dispatchers: DispatchersWrapper,
) : BaseValidationViewModel() {

    override fun errorHandler(error: Throwable) {
        if (error.message == "HTTP 400 Bad Request")
            _state.value = AuthState.ERROR_400
        else _state.value = AuthState.ERROR_WI_FI
    }

    fun getToken(email: String, password: String) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (tokenProvider.tokenContain()) tokenProvider.clearToken()
            _state.value = AuthState.LOADING
            tokenProvider.putToken(authUseCase.getToken(email, password).authToken)
            _state.value = AuthState.SUCCESS_AUTH
        }


    fun resetPassword(email: String) {
        viewModelScope.launch(dispatchers.io) {
            _state.value = AuthState.LOADING
            resetPasswordUseCase.resetPassword(email)
            _state.value = AuthState.SUCCESS_RESET
        }
    }


}

abstract class BaseValidationViewModel : BaseViewModel() {

    protected var isPasswordValidator = false
    protected var isEmailValidator = false

    override fun errorHandler(error: Throwable) {
        if (error.message == "HTTP 400 Bad Request")
            _state.value = AuthState.ERROR_400
        else _state.value = AuthState.ERROR_WI_FI
    }

    protected val _state = MutableStateFlow(AuthState.STARTED)
    val state = _state.asStateFlow()

    protected fun enableButton() {
        _state.value = if (isEmailValidator && isPasswordValidator)
            AuthState.ENABLED_BUTTON else AuthState.STARTED
    }

    fun emailValidation(email: String) {
        isEmailValidator = email.emailValidation()
        enableButton()
    }

    open fun passwordValidation(password: String) {
        isPasswordValidator = password.length >= 8
        enableButton()
    }
}