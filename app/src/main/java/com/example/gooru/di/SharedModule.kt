package com.example.gooru.di

import androidx.lifecycle.SavedStateHandle
import com.example.gooru.core.provide.AuthTokenProvider
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.DownloadProviderImpl
import com.example.gooru.core.provide.UserIdProvider
import org.koin.dsl.module

val sharedModule = module {

    single { AuthTokenProvider(get()) }

    single { UserIdProvider(get()) }

    factory<DownloadProvider> { DownloadProviderImpl(get()) }

    single { SavedStateHandle() }

}