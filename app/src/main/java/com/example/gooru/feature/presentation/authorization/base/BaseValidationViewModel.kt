package com.example.gooru.feature.presentation.authorization.base

import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.extensions.emailValidation
import com.example.gooru.core.states.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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