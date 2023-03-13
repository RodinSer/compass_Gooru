package com.example.gooru.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface  DispatchersWrapper {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
}