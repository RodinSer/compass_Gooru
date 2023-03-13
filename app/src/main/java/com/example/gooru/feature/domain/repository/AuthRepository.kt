package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.domain.model.token.UserToken

interface AuthRepository {

   suspend fun getToken(userName: String, password: String): UserToken

   suspend fun registration(userName: String, password: String)

   suspend fun resetPassword(email:String)

   suspend fun changePassword(new:String,old:String)
}