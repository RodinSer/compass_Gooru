package com.example.gooru.feature.domain.useCase.auth

interface RegistrationUseCase {

    suspend fun registration(userName:String,password:String)
}