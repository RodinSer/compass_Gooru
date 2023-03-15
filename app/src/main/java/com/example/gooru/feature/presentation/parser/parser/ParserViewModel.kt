package com.example.gooru.feature.presentation.parser.parser

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.constant.BASE_URL
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.core.provide.DownloadProvider
import com.example.gooru.core.provide.UserIdProvider
import com.example.gooru.feature.data.api.ParSourceApi
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.repository.PagingParser
import com.example.gooru.feature.domain.useCase.parser.DownLoadURLUseCase
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import kotlinx.coroutines.launch

class ParserViewModel(
    private val parser: PagingParser,
    private val favoriteUseCase: FavoriteUseCase,
    private val userIdProvider: UserIdProvider,
    private val downLoadURLUseCase: DownLoadURLUseCase,
    private val dispatchers: DispatchersWrapper,
    private val downloadProvider: DownloadProvider,
    private val editParserUseCasa: EditParserUseCasa
) : BaseViewModel() {

    fun parsers(id: Int) = parser.getParser(id)

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
                val file = downLoadURLUseCase.getUrl(parSourceId, parserId)
                val baseUrl = BASE_URL.removeRange(BASE_URL.length - 1, BASE_URL.length)

                val url = "$baseUrl$file.xlsx"

                downloadProvider.downloadFile(url, "$parSourceId-$parserId")
            }
        }

    fun editParser(item: Parser?) {
        viewModelScope.launch(dispatchers.io + handler) {

            if (item != null)
                editParserUseCasa.start(item.article, item.id)
        }
    }
}
