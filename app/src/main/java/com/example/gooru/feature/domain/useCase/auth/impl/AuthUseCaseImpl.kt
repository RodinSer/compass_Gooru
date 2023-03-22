package com.example.gooru.feature.domain.useCase.auth.impl

import com.example.gooru.feature.domain.repository.AuthRepository
import com.example.gooru.feature.domain.useCase.auth.AuthUseCase

class AuthUseCaseImpl(private val repository: AuthRepository) : AuthUseCase {

    override suspend fun getToken(userName: String, password: String) =
        repository.getToken(userName, password)
}