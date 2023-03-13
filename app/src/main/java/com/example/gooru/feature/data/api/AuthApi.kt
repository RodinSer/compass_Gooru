package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyChangePassword
import com.example.gooru.feature.data.body.BodyRegister
import com.example.gooru.feature.data.dto.auth.TokenDto
import com.example.gooru.feature.data.body.BodyUser
import com.example.gooru.feature.data.body.BodyUserEmail
import com.example.gooru.feature.data.dto.auth.RegistrationUserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST(AUTH_TOKEN)
    suspend fun getToken(@Body user: BodyUser): TokenDto

    @POST(AUTH_REGISTRATION)
    suspend fun registrationUser(@Body bodyUserRegister: BodyRegister): RegistrationUserDto

    @POST(AUTH_RESET_PASSWORD)
    suspend fun resetPassword(@Body bodyUserEmail: BodyUserEmail): Response<Unit>

    @POST(CHANGE_PASSWORD)
    suspend fun changePassword(@Body changePassword: BodyChangePassword)

    companion object {
        private const val AUTH_TOKEN = "auth/token/login/"
        private const val AUTH_REGISTRATION = "auth/users/"
        private const val AUTH_RESET_PASSWORD = "auth/users/reset_password/"
        private const val CHANGE_PASSWORD = "auth/users/set_password/"
    }
}