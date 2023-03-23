package com.example.gooru.feature.domain.useCase.user.impl

import com.example.gooru.feature.domain.model.body.BodyChangePassword
import com.example.gooru.feature.domain.repository.UserRepository
import com.example.gooru.feature.domain.useCase.user.UserChangePasswordUseCase

class UserChangePasswordUseCaseImpl(
    private val repository: UserRepository
) : UserChangePasswordUseCase {

    override suspend fun changePassword(newPassword: String, oldPassword: String) =
        repository.changePassword(BodyChangePassword(newPassword, oldPassword))
}