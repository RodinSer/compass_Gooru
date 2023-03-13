package com.example.gooru.feature.domain.useCase.auth

interface ChangePasswordUseCase {

    suspend fun changePassword(new:String,old:String)
}