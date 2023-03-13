package com.example.gooru.feature.domain.useCase.user

import com.example.gooru.feature.data.body.BodyUserUpdate
import com.example.gooru.feature.domain.model.homepage.user.User

interface UserUpdateUseCase {

    suspend fun userUpData(user: BodyUserUpdate):User
}