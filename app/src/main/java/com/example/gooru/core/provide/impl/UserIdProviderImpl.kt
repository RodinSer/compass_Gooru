package com.example.gooru.core.provide.impl

import android.content.Context
import com.example.gooru.core.provide.UserIdProvider

class UserIdProviderImpl(context: Context): UserIdProvider {

    private val preferenceUserId =
        context.getSharedPreferences(USER_ID_NAME, Context.MODE_PRIVATE)

    override fun putUserID(userId: Int) {
        preferenceUserId.edit().putInt(ID, userId).apply()

    }

    override fun clearUserId() {
        preferenceUserId.edit().clear().apply()
    }

    override fun getUserId(): Int =
        preferenceUserId.getInt(ID, -1)


    private companion object {
        const val USER_ID_NAME = "User_Id"
        const val ID = "IdFromUser"
    }
}