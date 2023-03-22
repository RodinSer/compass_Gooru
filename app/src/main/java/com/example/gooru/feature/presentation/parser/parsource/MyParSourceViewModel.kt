package com.example.gooru.feature.presentation.parser.parsource

import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.repository.ParSourceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyParSourceViewModel(
    private val homeRepository: ParSourceRepository,
    private val dispatcher:DispatchersWrapper
) : BaseViewModel() {

    private val _list = MutableStateFlow(emptyList<ParSourceHome>())
    val list = _list.asStateFlow()


    fun getMyParSource() {
        viewModelScope.launch(dispatcher.io+handler) {
            _loadState.value = LoadState.LOADING
            _list.value = homeRepository.getMyParsingTask()
            _loadState.value = LoadState.SUCCESS
        }

    }
}