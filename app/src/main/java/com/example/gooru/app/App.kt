package com.example.gooru.app

import android.app.Application
import com.example.gooru.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    init {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    dispatcherWrapperModule,
                    sharedModule,
                    useCaseModule,
                    userUseCaseModule,
                    authUseCaseModule,
                    parserUseCaseModule,
                    tariffUseCaseModule
                )
            )
        }
    }
}