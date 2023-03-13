package com.example.gooru.feature.domain.useCase.auth.impl

import com.example.gooru.feature.domain.repository.AuthRepository
import com.example.gooru.feature.domain.useCase.auth.ResetPasswordUseCase

class ResetPasswordUseCaseImpl(private val repository: AuthRepository) : ResetPasswordUseCase {

    override suspend fun resetPassword(email: String) = repository.resetPassword(email)
}