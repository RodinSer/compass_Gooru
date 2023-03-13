package com.example.gooru.feature.data.pref

import android.content.Context

class UserIdProvider(context: Context) {

    private val preferenceUserId =
        context.getSharedPreferences(USER_ID_NAME, Context.MODE_PRIVATE)

    fun putUserID(userId: Int) {
        preferenceUserId.edit().putInt(ID, userId).apply()

    }

    fun clearUserId() {
        preferenceUserId.edit().clear().apply()
    }

    fun getUserId(): Int =
        preferenceUserId.getInt(ID, -1)


    private companion object {
        const val USER_ID_NAME = "User_Id"
        const val ID = "IdFromUser"
    }
}