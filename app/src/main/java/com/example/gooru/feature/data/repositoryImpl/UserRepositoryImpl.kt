package com.example.gooru.feature.data.repositoryImpl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.gooru.core.constant.FORM_DATA
import com.example.gooru.feature.data.api.UsersApi
import com.example.gooru.feature.data.body.BodyChangePassword
import com.example.gooru.feature.data.body.BodyUserUpdate
import com.example.gooru.feature.domain.model.UserAvatar
import com.example.gooru.feature.domain.repository.UserRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream

class UserRepositoryImpl(
    private val userApi: UsersApi,
    private val context: Context
) : UserRepository {

    override suspend fun getUserInfo() = userApi.getUserInfo().toUser()

    override suspend fun userUpdate(user: BodyUserUpdate) = userApi.updateUser(user).toUser()

    override suspend fun changePassword(password: BodyChangePassword) {
        userApi.changePassword(password)
    }

    override suspend fun loadImage(uri: Uri?, userId: Int?): UserAvatar {
        return if (uri != null && userId != null) {

            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream)
            val byteArray: ByteArray = stream.toByteArray()

            val body = MultipartBody.Part.createFormData(
                FIELD_NAME, FILE_NAME + userId.toString() + FORMAT,
                byteArray.toRequestBody(FORM_DATA.toMediaType(), 0, byteArray.size)
            )

            userApi.uploadImage(body, userId).toUserAvatar()
        } else
            UserAvatar(PLUG)
    }

    private companion object {
        const val PLUG = ""
        const val FIELD_NAME = "avatar"
        const val FILE_NAME = "avatar_user_"
        const val FORMAT = ".png"
    }
}