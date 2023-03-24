package com.example.gooru.feature.presentation.authorization.auth

import androidx.lifecycle.viewModelScope
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.AuthTokenProvider
import com.example.gooru.core.states.AuthState
import com.example.gooru.feature.domain.useCase.auth.AuthUseCase
import com.example.gooru.feature.domain.useCase.auth.ResetPasswordUseCase
import com.example.gooru.feature.presentation.authorization.base.BaseValidationViewModel
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