package com.example.gooru.feature.domain.repository

import android.net.Uri
import com.example.gooru.feature.domain.model.body.BodyChangePassword
import com.example.gooru.feature.domain.model.body.BodyUserUpdate
import com.example.gooru.feature.domain.model.UserAvatar
import com.example.gooru.feature.domain.model.homepage.user.User

interface UserRepository {

    suspend fun getUserInfo(): User

    suspend fun userUpdate(user: BodyUserUpdate): User

    suspend fun changePassword(password: BodyChangePassword)

    suspend fun loadImage(uri: Uri?, userId: Int?): UserAvatar

}