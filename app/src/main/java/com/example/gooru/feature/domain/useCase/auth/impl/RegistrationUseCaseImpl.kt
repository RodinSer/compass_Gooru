package com.example.gooru.feature.domain.useCase.auth.impl

import com.example.gooru.feature.domain.repository.AuthRepository
import com.example.gooru.feature.domain.useCase.auth.RegistrationUseCase

class RegistrationUseCaseImpl(private val repository: AuthRepository) : RegistrationUseCase {

    override suspend fun registration(userName: String, password: String) =
        repository.registration(userName, password)
}