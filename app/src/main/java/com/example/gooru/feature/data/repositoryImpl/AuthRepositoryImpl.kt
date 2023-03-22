package com.example.gooru.feature.data.repositoryImpl

import com.example.gooru.feature.data.api.AuthApi
import com.example.gooru.feature.data.body.BodyChangePassword
import com.example.gooru.feature.data.body.BodyRegister
import com.example.gooru.feature.data.body.BodyUser
import com.example.gooru.feature.data.body.BodyUserEmail
import com.example.gooru.feature.domain.repository.AuthRepository

class AuthRepositoryImpl(private val api: AuthApi) : AuthRepository {

    override suspend fun getToken(userName: String, password: String) =
        api.getToken(BodyUser(userName, password)).toUserToken()

    override suspend fun registration(userName: String, password: String) {
        api.registrationUser(BodyRegister(userName, password, userName))
    }

    override suspend fun resetPassword(email: String) {
        api.resetPassword(BodyUserEmail(email))
    }

    override suspend fun changePassword(new: String, old: String) =
        api.changePassword(BodyChangePassword(new, old))
}