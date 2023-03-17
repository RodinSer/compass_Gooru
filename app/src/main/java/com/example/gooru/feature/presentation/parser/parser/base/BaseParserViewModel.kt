package com.example.gooru.feature.presentation.parser.parser.base

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.useCase.parser.CommentUseCase
import com.example.gooru.feature.domain.useCase.parser.DownLoadURLUseCase
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseParserViewModel(
    private val favoriteUseCase: FavoriteUseCase,
    private val userIdProvider: UserIdProvider,
    private val downLoadURLUseCase: DownLoadURLUseCase,
    private val dispatchers: DispatchersWrapper,
    private val downloadProvider: DownloadProvider,
    private val editParserUseCasa: EditParserUseCasa,
    private val commentUseCase: CommentUseCase,
) : BaseViewModel() {

    abstract fun parsers(parSourceId: Int?=null): Flow<PagingData<Parser>>

    fun worKFavorite(parser: Parser?) {
        viewModelScope.launch(dispatchers.io + handler) {

            _loadState.value = LoadState.LOADING
            if (parser != null)
                favoriteUseCase.workFavorite(userIdProvider.getUserId(), parser)
            _loadState.value = LoadState.ERROR
        }
    }

    fun downLoad(parSourceId: Int, parserId: Int?) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (parserId != null) {
                val url = downLoadURLUseCase.getUrl(parSourceId, parserId)
                downloadProvider.downloadFile(url, "$parSourceId-$parserId")
            }

        }

    fun editParser(item: Parser?) {
        viewModelScope.launch(dispatchers.io + handler) {
            if (item != null)
                editParserUseCasa.start(item.article, item.id)
        }
    }

    fun commentWork(item: Parser?) {
        viewModelScope.launch(dispatchers.io + handler) {
            if (item != null)
                commentUseCase.doWork(item.id, item.comment, item.commentId)
        }
    }
}