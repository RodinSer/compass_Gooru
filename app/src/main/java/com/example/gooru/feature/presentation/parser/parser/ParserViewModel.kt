package com.example.gooru.feature.presentation.parser.parser

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gooru.core.LoadState
import com.example.gooru.core.base.BaseViewModel
import com.example.gooru.core.dispatcher.DispatchersWrapper
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.pref.UserIdProvider
import com.example.gooru.feature.domain.repository.PagingParser
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.io.File

class ParserViewModel(
    private val parser: PagingParser,
    private val favoriteUseCase: FavoriteUseCase,
    private val userIdProvider: UserIdProvider,
    private val dispatchers: DispatchersWrapper
) : BaseViewModel() {

    fun parsers(id: Int) = parser.getParser(id)

    fun worKFavorite( parserId: Int?) {
        viewModelScope.launch (dispatchers.io+handler){
            if (parserId != null){
                _loadState.value = LoadState.LOADING
                favoriteUseCase.workFavorite(userIdProvider.getUserId(), parserId)
                _loadState.value = LoadState.ERROR
            }
        }
    }

        fun downLoad(url: String, downloadManager: DownloadManager) {
            val downloadRequest = DownloadManager
                .Request(Uri.parse(url))
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)//качать только по войфай или нет
                .setTitle("загрузка")
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    File.separator + "test.jpg")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // показать сообщение о скачивании

            downloadManager.enqueue(downloadRequest)
    }
}