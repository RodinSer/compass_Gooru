package com.example.gooru.feature.domain.useCase.auth.impl

import com.example.gooru.feature.domain.repository.AuthRepository
import com.example.gooru.feature.domain.useCase.auth.ChangePasswordUseCase

class ChangePasswordUseCaseImpl(private val repository: AuthRepository) : ChangePasswordUseCase {
    override suspend fun changePassword(new: String, old: String) =
        repository.changePassword(new, old)

}