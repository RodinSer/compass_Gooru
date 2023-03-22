package com.example.gooru.feature.domain.useCase.user.impl

import com.example.gooru.feature.domain.repository.UserRepository
import com.example.gooru.feature.domain.useCase.user.UserInfoUseCase

class UserInfoUseCaseImpl(private val repository: UserRepository) : UserInfoUseCase {

    override suspend fun getUserInfo() = repository.getUserInfo()
}