package com.example.gooru.feature.presentation.parsource.addparsource

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.states.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.generation.ExchangeParsing
import com.example.gooru.core.generation.createExchangeParsing
import com.example.gooru.feature.domain.useCase.parsource.NewParSourceUseCase
import kotlinx.coroutines.launch

class AddParSourceViewModel(
    private val newParSourceUseCase: NewParSourceUseCase,
    private val dispatchers: DispatchersWrapper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

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
    ) = viewModelScope.launch(dispatchers.io + handler) {
        _loadState.value = LoadState.LOADING
        savedStateHandle[NEW_PAR_SOURCE] = newParSourceUseCase
            .crete(dataType, description, freelanceSource, keywords, name)
        _loadState.value = LoadState.SUCCESS
    }

    companion object {
        const val NEW_PAR_SOURCE = "new_parSource"
    }
}