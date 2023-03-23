package com.example.gooru.core.provide.impl

import android.content.Context
import com.example.gooru.core.provide.NightModeProvider
import java.security.AccessControlContext

class NightModeProviderImpl(context: Context) : NightModeProvider {

    private val preferenceNightMode =
        context.getSharedPreferences(NIGHT_MODE, Context.MODE_PRIVATE)


    override fun setNightMode(isNightMode: Boolean) {
        preferenceNightMode.edit().putBoolean(IS_NIGHT,isNightMode).apply()
    }

    override fun checkNightMode() = preferenceNightMode.getBoolean(IS_NIGHT, false)


    private companion object {
        const val NIGHT_MODE = "night_mode_28"
        const val IS_NIGHT = "is_night_mode_28"
    }
}