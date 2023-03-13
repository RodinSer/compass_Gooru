package com.example.gooru.feature.data.pref

import android.content.Context
import android.util.Log

class AuthTokenProvider(context: Context) {

    private val preferenceToken =
        context.getSharedPreferences(TOKEN_SHARED_NAME, Context.MODE_PRIVATE)

    fun putToken(token: String) {
        preferenceToken.edit().putString(TOKEN, token).apply()
    }

    fun clearToken(){
        preferenceToken.edit().clear().apply()
    }

    fun getToken(): String? =
        preferenceToken.getString(TOKEN, null)


    fun tokenContain(): Boolean {
        return getToken() != null
    }

    companion object {
        private const val TOKEN_SHARED_NAME = "Pref_Token"
        private const val TOKEN = "token"
    }
}