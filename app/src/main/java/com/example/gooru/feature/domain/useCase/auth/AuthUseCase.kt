package com.example.gooru.feature.domain.useCase.auth

import com.example.gooru.feature.domain.model.token.UserToken

interface AuthUseCase {

    suspend fun getToken(userName:String,password:String): UserToken
}