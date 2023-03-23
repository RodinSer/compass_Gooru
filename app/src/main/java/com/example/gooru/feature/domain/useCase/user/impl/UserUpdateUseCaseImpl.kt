package com.example.gooru.feature.domain.useCase.user.impl

import com.example.gooru.feature.domain.model.body.BodyUserUpdate
import com.example.gooru.feature.domain.repository.UserRepository
import com.example.gooru.feature.domain.useCase.user.UserUpdateUseCase

class UserUpdateUseCaseImpl(private val repository: UserRepository) : UserUpdateUseCase {

    override suspend fun userUpData(user: BodyUserUpdate) =
        repository.userUpdate(user)

}