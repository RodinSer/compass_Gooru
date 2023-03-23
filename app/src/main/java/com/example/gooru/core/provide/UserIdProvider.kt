package com.example.gooru.core.provide

interface UserIdProvider{

    fun putUserID(userId: Int)

    fun clearUserId()

    fun getUserId(): Int
}