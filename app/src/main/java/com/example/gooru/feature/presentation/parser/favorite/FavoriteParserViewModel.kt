package com.example.gooru.feature.presentation.parser.favorite

import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.domain.repository.ParserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteParserViewModel(
    private val parserRepository: ParserRepository,
    private val dispatcher: DispatchersWrapper
    ) : BaseViewModel() {

    private val _listFavorite = MutableStateFlow(emptyList<FavoriteParserDto>())
    val list = _listFavorite.asStateFlow()

    init {
        getFavoriteList()
    }

    fun getFavoriteList() {
        viewModelScope.launch(dispatcher.io+handler) {
            _loadState.value = LoadState.LOADING
            delay(100)
            _listFavorite.value = parserRepository.getListFavorite()
            _loadState.value = LoadState.SUCCESS
        }
    }
}