package com.example.gooru.core

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class Test:WebSocketListener() {
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }


    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }
}