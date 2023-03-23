package com.example.gooru.di

import androidx.lifecycle.SavedStateHandle
import com.example.gooru.core.provide.AuthTokenProvider
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.MultipartBodyProvider
import com.example.gooru.core.provide.impl.DownloadProviderImpl
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.core.provide.impl.AuthTokenProviderImpl
import com.example.gooru.core.provide.impl.MultipartBodyProviderImpl
import com.example.gooru.core.provide.impl.UserIdProviderImpl
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
}

