package com.example.gooru.feature.presentation.parser.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.domain.repository.ParserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteParserViewModel(private val parserRepository: ParserRepository) : ViewModel() {

    private val _listFavorite = MutableStateFlow(emptyList<FavoriteParserDto>())
    val list = _listFavorite.asStateFlow()

    fun getFavoriteList() {
        viewModelScope.launch {
            _listFavorite.value = parserRepository.getListFavorite()
        }
    }
}