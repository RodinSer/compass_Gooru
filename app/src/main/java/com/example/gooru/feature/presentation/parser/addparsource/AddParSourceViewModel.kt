package com.example.gooru.feature.presentation.parser.addparsource

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.useCase.parsource.NewParSourceUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddParSourceViewModel(
    private val newParSourceUseCase: NewParSourceUseCase,
    private val dispatchers: DispatchersWrapper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _isBackStack = MutableStateFlow<ParSourceHome?>(null)
    val isBackStack = _isBackStack.asStateFlow()

    init {
        _loadState.value = LoadState.STARTED
    }

    fun getExchangeParsing() = createExchangeParsing()

    fun creteParSource(
        dataType: Int,
        description: String,
        freelanceSource: List<ExchangeParsing>,
        keywords: List<String>,
        name: String,
    ) = viewModelScope.launch  (dispatchers.io + handler)  {
        _loadState.value = LoadState.LOADING
        savedStateHandle[NEW_PAR_SOURCE] = newParSourceUseCase.crete(dataType, description, freelanceSource, keywords, name)
        _loadState.value = LoadState.SUCCESS
    }

    companion object{
        val NEW_PAR_SOURCE = "new_parSource"
    }

}