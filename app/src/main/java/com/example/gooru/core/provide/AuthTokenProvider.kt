package com.example.gooru.core.provide

interface AuthTokenProvider{

    fun putToken(token: String)

    fun clearToken()

    fun getToken(): String?

    fun tokenContain(): Boolean
}