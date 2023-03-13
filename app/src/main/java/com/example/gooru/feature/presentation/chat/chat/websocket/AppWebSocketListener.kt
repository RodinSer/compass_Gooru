package com.example.gooru.feature.presentation.chat.chat.websocket

import android.util.Log
import com.example.gooru.feature.data.dto.support.SupportTicketDto
import com.example.gooru.feature.data.dto.support.chat.ChatMessage
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import retrofit2.converter.gson.GsonConverterFactory


class AppWebSocketListener(private val action: (text: String) -> Unit) : WebSocketListener() {

    private val gson: Gson = GsonBuilder().create()

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d("Kart", "Start")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        action(text)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.e("Kart", "Finish")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.e("Kart", "Fail ${t.message}")
    }
}