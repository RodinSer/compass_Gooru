package com.example.gooru.core.provide

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: AuthTokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e("Kart", "интецептор токен = ${tokenProvider.getToken()}")
        val token = if (tokenProvider.getToken() != null) "$TOKEN ${tokenProvider.getToken()}"
        else EMPTY

        val request = chain.request().newBuilder()
            .addHeader(AUTHORIZATION, token)
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
        const val TOKEN = "token"
        const val EMPTY = ""
    }
}