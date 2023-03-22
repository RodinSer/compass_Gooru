package com.example.gooru.di

import com.example.gooru.core.constant.BASE_URL
import com.example.gooru.feature.data.api.*
import com.example.gooru.core.provide.AuthInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


val networkModule = module {

    single { AuthInterceptor(get()) }

    single(named("authHeader")) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }


    single(named("webSocket")) {
        OkHttpClient.Builder().build()
    }

    single { GsonBuilder().create() }

    single(named("retrofit")) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get(named("authHeader")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<AuthApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single<ParSourceApi> {
        get<Retrofit>(named("retrofit")).create()
    }
    single<UsersApi> {
        get<Retrofit>(named("retrofit")).create()
    }
    single<TariffApi> {
        get<Retrofit>(named("retrofit")).create()
    }

    single<SupportApi> {
        get<Retrofit>(named("retrofit")).create()
    }

}