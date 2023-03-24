package com.example.gooru.app

import androidx.lifecycle.ViewModel
import com.example.gooru.core.provide.NightModeProvider

class MainViewModel(
    private val nightModeProvider: NightModeProvider
) : ViewModel() {

    fun nightModeProvider() = nightModeProvider.checkNightMode()
}