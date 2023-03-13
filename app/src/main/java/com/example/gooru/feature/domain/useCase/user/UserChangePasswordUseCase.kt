package com.example.gooru.feature.domain.useCase.user

interface UserChangePasswordUseCase {

    suspend fun changePassword(newPassword: String,oldPassword: String)
}