package com.example.gooru.core.provide

import android.net.Uri
import okhttp3.MultipartBody

interface MultipartBodyProvider {

    fun createBody(uri: Uri, userId: Int, quality: Int = 80): MultipartBody.Part
}