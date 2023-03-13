package com.example.gooru.feature.domain.useCase.auth

interface ResetPasswordUseCase {

    suspend fun resetPassword(email:String)
}