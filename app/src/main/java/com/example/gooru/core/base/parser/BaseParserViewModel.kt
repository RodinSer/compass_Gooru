package com.example.gooru.core.base.parser

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.gooru.core.states.LoadState
import com.example.gooru.core.states.ParserButton
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.useCase.parser.CommentUseCase
import com.example.gooru.feature.domain.useCase.parser.DownLoadURLUseCase
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import com.example.gooru.feature.presentation.parsers.parser.changes.LocalChangesParser
import com.example.gooru.feature.presentation.parsers.parser.changes.OnChangeParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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

    var parsers: Flow<PagingData<Parser>>? = null

    private val localChanges = LocalChangesParser()
    protected val localChangesFlow = MutableStateFlow(OnChangeParser(localChanges))

    abstract fun getParsers(parSourceId: Int? = null)

    protected fun merge(
        pagingParser: PagingData<Parser>,
        localChanges: OnChangeParser<LocalChangesParser>
    ): PagingData<Parser> = pagingParser.map { parser ->
        localChanges.value.parser[parser.id] ?: parser
    }

    private fun worKFavorite(parser: Parser?) =
        viewModelScope.launch(dispatchers.io + handler) {
            _loadState.value = LoadState.LOADING
            if (parser != null) {
                val favoriteId = favoriteUseCase.workFavorite(userIdProvider.getUserId(), parser)
                localChanges.parser[parser.id] =
                    parser.copy(isFavorite = !parser.isFavorite, favoriteId = favoriteId)
                localChangesFlow.value = OnChangeParser(localChanges)
            }
            _loadState.value = LoadState.SUCCESS
        }

    private fun downLoad(parSourceId: Int?, parserId: Int?) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (parserId != null && parSourceId != null) {
                val url = downLoadURLUseCase.getUrl(parSourceId, parserId)
                downloadProvider.downloadFile(url, "$parSourceId-$parserId")
            }
        }

    private fun saveNewArticle(parser: Parser?) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (parser != null) {
                editParserUseCasa.start(parser.article, parser.id)
                editArticleVisibility(parser)
            }
        }

    private fun commentWork(parser: Parser?) =
        viewModelScope.launch(dispatchers.io + handler) {
            if (parser != null) {
                val newId = commentUseCase.doWork(parser.id, parser.comment, parser.commentId)
                val isComment = newId != null
                localChanges.parser[parser.id] = parser.copy(
                    comment = parser.comment,
                    commentId = newId,
                    isCommentFull = isComment
                )
                localChangesFlow.value = OnChangeParser(localChanges)
            }
        }

    private fun commentExpand(parser: Parser?) {
        if (parser != null) {
            localChanges.parser[parser.id] =
                parser.copy(isCommentVisibility = !parser.isCommentVisibility)
            localChangesFlow.value = OnChangeParser(localChanges)
        }
    }

    private fun descriptionsExpand(parser: Parser?) {
        if (parser != null) {
            val maxLine = if (parser.maxTextLine == 3) Int.MAX_VALUE else 3
            localChanges.parser[parser.id] = parser.copy(maxTextLine = maxLine)
            localChangesFlow.value = OnChangeParser(localChanges)
        }
    }

    private fun editArticleVisibility(parser: Parser?) {
        if (parser != null) {
            localChanges.parser[parser.id] = parser.copy(isEditArticle = !parser.isEditArticle)
            localChangesFlow.value = OnChangeParser(localChanges)
        }
    }

    fun addListenerPersonButton(button: ParserButton) {
        when (button) {
            ParserButton.EDIT_ARTICLE -> editArticleVisibility(button.item)
            ParserButton.DOWNLOAD -> downLoad(button.item?.parSource, button.item?.id)
            ParserButton.FAVORITE -> worKFavorite(button.item)
            ParserButton.SAVE_COMMENT -> commentWork(button.item)
            ParserButton.COMMENT_EXPAND -> commentExpand(button.item)
            ParserButton.DESCRIPTIONS_EXPAND -> descriptionsExpand(button.item)
            ParserButton.SAVE_ARTICLE -> saveNewArticle(button.item)
            else -> {}
        }
    }
}