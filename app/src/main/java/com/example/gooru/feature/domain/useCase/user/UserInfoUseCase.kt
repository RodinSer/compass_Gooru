package com.example.gooru.feature.domain.useCase.user

import com.example.gooru.feature.domain.model.homepage.user.User

interface UserInfoUseCase {

    suspend fun getUserInfo():User
}