package com.example.gooru.feature.presentation.authorization.registration

import androidx.lifecycle.viewModelScope
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.extensions.passwordValidation
import com.example.gooru.feature.domain.useCase.auth.RegistrationUseCase
import com.example.gooru.core.states.AuthState
import com.example.gooru.feature.presentation.authorization.base.BaseValidationViewModel
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val dispatchers: DispatchersWrapper,
    private val registerUseCase: RegistrationUseCase
) : BaseValidationViewModel() {

    private var repeatPassword = ""
    private var password = ""

    fun registration(email: String, password: String, repeatPassword: String) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (password == repeatPassword && password.passwordValidation()) {
                _state.value = AuthState.LOADING
                registerUseCase.registration(email, password)
                _state.value = AuthState.SUCCESS_AUTH
            }
        }

    override fun passwordValidation(password: String) {
        this.password = password
        isPasswordValidator = password.passwordValidation() && password == repeatPassword
        enableButton()
    }

    fun repeatPasswordValidation(password: String) {
        repeatPassword = password
        isPasswordValidator = this.password.passwordValidation() && this.password == password
        enableButton()
    }
}