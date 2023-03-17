package com.example.gooru.feature.presentation.parser.parser.parserp

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.repository.PagingParser
import com.example.gooru.feature.domain.useCase.parser.CommentUseCase
import com.example.gooru.feature.domain.useCase.parser.DownLoadURLUseCase
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import com.example.gooru.feature.presentation.parser.parser.ParserGrope
import com.example.gooru.feature.presentation.parser.parser.base.BaseParserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.plus

class ParserViewModel(
    private val parser: PagingParser,
    favoriteUseCase: FavoriteUseCase,
    userIdProvider: UserIdProvider,
    downLoadURLUseCase: DownLoadURLUseCase,
    dispatchers: DispatchersWrapper,
    downloadProvider: DownloadProvider,
    editParserUseCasa: EditParserUseCasa,
    commentUseCase: CommentUseCase,
) : BaseParserViewModel(
    favoriteUseCase,
    userIdProvider,
    downLoadURLUseCase,
    dispatchers,
    downloadProvider,
    editParserUseCasa,
    commentUseCase
) {
    private val _radioButtonId = MutableStateFlow(ParserGrope.All.int)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun parsers(parSourceId: Int?): Flow<PagingData<Parser>> = _radioButtonId.asStateFlow()
        .flatMapLatest { buttonId -> parser.getParserByParSourceId(parSourceId!!, buttonId) }
        .cachedIn(viewModelScope + handler)

    fun setRadioButtonId(id: Int) {
        _radioButtonId.value = id
    }

}
