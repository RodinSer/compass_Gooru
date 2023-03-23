package com.example.gooru.feature.data.repositoryImpl

import android.net.Uri
import com.example.gooru.core.provide.MultipartBodyProvider
import com.example.gooru.feature.data.api.UsersApi
import com.example.gooru.feature.domain.model.body.BodyChangePassword
import com.example.gooru.feature.domain.model.body.BodyUserUpdate
import com.example.gooru.feature.domain.model.UserAvatar
import com.example.gooru.feature.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApi: UsersApi,
    private val multipartBodyProvider: MultipartBodyProvider,
) : UserRepository {

    override suspend fun getUserInfo() = userApi.getUserInfo().toUser()

    override suspend fun userUpdate(user: BodyUserUpdate) = userApi.updateUser(user).toUser()

    override suspend fun changePassword(password: BodyChangePassword) {
        userApi.changePassword(password)
    }

    override suspend fun loadImage(uri: Uri?, userId: Int?): UserAvatar {
        return if (uri != null && userId != null) {

            userApi.uploadImage(multipartBodyProvider.createBody(uri, userId), userId)
                .toUserAvatar()
        } else
            UserAvatar(PLUG)
    }

    private companion object {
        const val PLUG = ""
    }
}

