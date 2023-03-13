package com.example.gooru.feature.presentation.authorization.registration

import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.extensions.passwordValidation
import com.example.gooru.feature.domain.useCase.auth.RegistrationUseCase
import com.example.gooru.feature.presentation.authorization.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val dispatchers: DispatchersWrapper,
    private val registerUseCase: RegistrationUseCase
) : BaseViewModel() {

    override fun errorHandler(error: Throwable) {
        if (error.message == "HTTP 400 Bad Request")
            _stateRegistration.value = AuthState.ERROR_400
        else _stateRegistration.value = AuthState.ERROR_WI_FI
    }

    private val _stateRegistration = MutableStateFlow(AuthState.STARTED)
    val stateRegistration = _stateRegistration.asStateFlow()

    fun registration(email: String, password: String, repeatPassword: String) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (password == repeatPassword && password.passwordValidation()) {
                _stateRegistration.value = AuthState.LOADING
                registerUseCase.registration(email, password)
                _stateRegistration.value = AuthState.SUCCESS_AUTH
            }
        }
}