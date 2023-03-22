package com.example.gooru.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gooru.core.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _loadState = MutableStateFlow(LoadState.STARTED)
    val loadState = _loadState.asStateFlow()

    protected open fun errorHandler(error: Throwable) {
        Log.e("Kart", "${error.message}")
        if (error.message == "HTTP 400")
            _loadState.value = LoadState.ERROR
        else _loadState.value = LoadState.ERROR
    }

    protected val handler = CoroutineExceptionHandler { _, T ->

        errorHandler(T)
    }
}