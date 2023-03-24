package com.example.gooru.feature.presentation.parsers.parser.parserp

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gooru.core.base.parser.BaseParserViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.repository.PagingParser
import com.example.gooru.feature.domain.useCase.parser.CommentUseCase
import com.example.gooru.feature.domain.useCase.parser.DownLoadURLUseCase
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import com.example.gooru.feature.domain.useCase.parsource.ParSourceByIdUseCase
import com.example.gooru.feature.presentation.parsers.parser.ParserGrope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class ParserViewModel(
    private val parser: PagingParser,
    private val parSourceByIdUseCasa: ParSourceByIdUseCase,
    favoriteUseCase: FavoriteUseCase,
    userIdProvider: UserIdProvider,
    private val downLoadURLUseCase: DownLoadURLUseCase,
    private val dispatchers: DispatchersWrapper,
    private val downloadProvider: DownloadProvider,
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

    private val _parSource = MutableStateFlow<ParSourceHome?>(null)
    val parSource = _parSource.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getParsers(parSourceId: Int?) {
        parsers = _radioButtonId.asStateFlow()
            .flatMapLatest { buttonId -> parser.getParserByParSourceId(parSourceId!!, buttonId) }
            .cachedIn(viewModelScope + handler)
            .combine(localChangesFlow, this::merge).cachedIn(viewModelScope)
    }

    fun setRadioButtonId(id: Int) {
        _radioButtonId.value = id
    }

    fun getParSourceInfo(parSourceId: Int) {
        viewModelScope.launch(dispatchers.io + handler) {
            _parSource.value = parSourceByIdUseCasa.getParSource(parSourceId)
        }
    }

    fun downloadUrlAllParsersByIdParSource(parSourceId:Int) {
        viewModelScope.launch(dispatchers.io + handler) {
            downloadProvider.downloadFile(downLoadURLUseCase.getUrl(parSourceId,null),parSourceId.toString())
        }
    }
}
