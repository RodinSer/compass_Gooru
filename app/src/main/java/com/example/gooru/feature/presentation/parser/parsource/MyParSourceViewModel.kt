package com.example.gooru.feature.presentation.parser.parsource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.repository.ParSourceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyParSourceViewModel(
    private val homeRepository: ParSourceRepository
) : ViewModel() {

    private val _list = MutableStateFlow(emptyList<ParSourceHome>())
    val list = _list.asStateFlow()


    fun getMyParSource() {
        viewModelScope.launch {
            _list.value = homeRepository.getMyParsingTask()
        }

    }
}