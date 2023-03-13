package com.example.gooru.feature.data.pref

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: AuthTokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("Kart", "интецептор токен = ${tokenProvider.getToken()}")
        val token = if (tokenProvider.getToken() != null) "token ${tokenProvider.getToken()}"
        else ""
        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(request)
    }
}