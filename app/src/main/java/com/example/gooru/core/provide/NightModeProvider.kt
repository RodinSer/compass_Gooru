package com.example.gooru.core.provide

interface NightModeProvider{

    fun setNightMode(isNightMode: Boolean)

    fun checkNightMode(): Boolean

}