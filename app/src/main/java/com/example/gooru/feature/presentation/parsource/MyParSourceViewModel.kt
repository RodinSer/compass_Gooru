package com.example.gooru.feature.presentation.parsource

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.states.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.repository.ParSourceRepository
import com.example.gooru.feature.presentation.parsers.addparsource.AddParSourceViewModel.Companion.NEW_PAR_SOURCE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyParSourceViewModel(
    private val homeRepository: ParSourceRepository,
    private val dispatcher:DispatchersWrapper,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val _list = MutableStateFlow(emptyList<ParSourceHome>())
    val list = _list.asStateFlow()

    init {
        getMyParSource()
    }

    fun getMyParSource() =
        viewModelScope.launch(dispatcher.io+handler) {
            _loadState.value = LoadState.LOADING
            _list.value = homeRepository.getMyParsingTask()
            _loadState.value = LoadState.SUCCESS
        }

    fun checkNewItem() {
       val newParSource: ParSourceHome? =  savedStateHandle[NEW_PAR_SOURCE]
        val list =  _list.value.toMutableList()
        if (newParSource!=null)  {
            list.add(newParSource)
            _list.value = list
            savedStateHandle[NEW_PAR_SOURCE] = null
        }
    }
}