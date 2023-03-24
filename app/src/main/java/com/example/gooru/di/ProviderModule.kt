package com.example.gooru.di

import androidx.lifecycle.SavedStateHandle
import com.example.gooru.core.provide.*
import com.example.gooru.core.provide.impl.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModule = module {

    single { SavedStateHandle() }

}

val providerModule = module {

    singleOf(::AuthTokenProviderImpl) { bind<AuthTokenProvider>() }

    singleOf(::UserIdProviderImpl) { bind<UserIdProvider>() }

    singleOf(::DownloadProviderImpl) { bind<DownloadProvider>() }

    singleOf(::MultipartBodyProviderImpl) {bind<MultipartBodyProvider>()}

    singleOf(::NightModeProviderImpl) {bind<NightModeProvider>()}
}

