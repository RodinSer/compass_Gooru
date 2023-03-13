package com.example.gooru.di

import com.example.gooru.core.dispatcher.CoroutinesDispatchersWrapper
import com.example.gooru.core.dispatcher.DispatchersWrapper
import org.koin.dsl.module

val dispatcherWrapperModule = module {

    single<DispatchersWrapper> { CoroutinesDispatchersWrapper() }
}