package com.example.gooru.core.provide.impl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.gooru.core.constant.FORM_DATA
import com.example.gooru.core.provide.MultipartBodyProvider
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MultipartBodyProviderImpl(private val context: Context) : MultipartBodyProvider {

    override fun createBody(uri: Uri, userId: Int, quality: Int): MultipartBody.Part {

        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)

        val bitmap = BitmapFactory.decodeStream(inputStream)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, stream)
        val byteArray: ByteArray = stream.toByteArray()

        return MultipartBody.Part.createFormData(
            FIELD_NAME, FILE_NAME + userId.toString() + FORMAT,
            byteArray.toRequestBody(FORM_DATA.toMediaType(), 0, byteArray.size)
        )

    }

    private companion object {
        const val FIELD_NAME = "avatar"
        const val FILE_NAME = "avatar_user_"
        const val FORMAT = ".png"
    }
}