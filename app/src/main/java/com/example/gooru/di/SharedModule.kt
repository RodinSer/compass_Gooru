package com.example.gooru.di

import com.example.gooru.feature.data.pref.AuthTokenProvider
import com.example.gooru.feature.data.pref.UserIdProvider
import org.koin.dsl.module

val sharedModule = module{

    single { AuthTokenProvider(get()) }
    single { UserIdProvider(get()) }

}