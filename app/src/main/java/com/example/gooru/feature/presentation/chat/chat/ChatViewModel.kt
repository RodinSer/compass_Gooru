package com.example.gooru.feature.presentation.chat.chat

import androidx.lifecycle.ViewModel
import com.example.gooru.core.extensions.simpleDateFormat
import com.example.gooru.feature.data.dto.support.chat.ChatMessage
import com.example.gooru.feature.data.dto.support.chat.SendMessage
import com.example.gooru.feature.data.pref.AuthTokenProvider
import com.example.gooru.feature.data.pref.UserIdProvider
import com.example.gooru.feature.presentation.chat.chat.websocket.AppWebSocketListener
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class ChatViewModel(
    private val client: OkHttpClient,
    private val gson: Gson,
    private val tokenProvider: AuthTokenProvider,
    private val userIdProvider: UserIdProvider
) : ViewModel() {

    private val _message = MutableStateFlow<List<ChatMessage>>(mutableListOf())
    val message = _message.asStateFlow()

    private val webSocketListener = AppWebSocketListener { json -> messageListener(json) }

    private var webSocket: WebSocket? = null

    private fun messageListener(json: String) {
        val newList = _message.value.toMutableList()
        val newItem: ChatMessage = gsonConvectorFromJson(json)
        newItem.created_at = newItem.created_at.simpleDateFormat()
        newList.add(newItem)
        _message.value = newList
    }

    fun startWebSocket(ticketId: Int) {
        val request = Request.Builder()
            .url("$BASE_WS_URL/$ticketId/$AUTH ${tokenProvider.getToken()}")
            .build()

        webSocket = client.newWebSocket(request, webSocketListener)
    }

    fun sendMessage(text: String) {
        webSocket?.send(gson.toJson(SendMessage(text)))
    }

    fun closeWebSocKet() {
        webSocket?.cancel()
    }

    private inline fun <reified M : Any> gsonConvectorFromJson(text: String): M {
        return gson.fromJson(text, M::class.java)
    }

    fun getUserId() = userIdProvider.getUserId()

    private companion object {
        const val BASE_WS_URL = "ws://10.10.10.88:8003/ws/chat"
        const val AUTH = "?Authorization=token"
    }

}