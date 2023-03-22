package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyChangePassword
import com.example.gooru.feature.data.body.BodyUserUpdate
import com.example.gooru.feature.data.dto.user.AvatarDto
import com.example.gooru.feature.data.dto.user.UserDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UsersApi {

    @GET(USER_ME)
    suspend fun getUserInfo(): UserDto

    @PATCH(USER_ME)
    suspend fun updateUser(@Body userUpdate: BodyUserUpdate): UserDto

    @POST(CHANGE_PASSWORD)
    suspend fun changePassword(@Body password: BodyChangePassword): Response<Unit>

    @Multipart
    @PUT(UPLOAD_AVATAR)
    suspend fun uploadImage(
        @Part avatar: MultipartBody.Part,
        @Path("id") userId: Int,
    ): AvatarDto

    companion object {
        private const val USER_ME = "auth/users/me/"
        private const val CHANGE_PASSWORD = "auth/users/set_password/"
        private const val UPLOAD_AVATAR = "api/v2/user/upload/{id}/"
    }
}

