package com.example.gooru.feature.presentation.parser.addparsource

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
) : BaseViewModel() {

    private val _isBackStack = MutableStateFlow<ParSourceHome?>(null)
    val isBackStack = _isBackStack.asStateFlow()

    fun getExchangeParsing() = createExchangeParsing()

    suspend fun creteParSource(
        dataType: Int,
        description: String,
        freelanceSource: List<ExchangeParsing>,
        keywords: List<String>,
        name: String,
    ) = withContext(viewModelScope.coroutineContext + dispatchers.io + handler) {
        /*_loadState.value = LoadState.LOADING
        _loadState.value = LoadState.SUCCESS
        newParSourceUseCase.crete(dataType, description, freelanceSource, keywords, name)*/
        "ghbdtn"
    }

}