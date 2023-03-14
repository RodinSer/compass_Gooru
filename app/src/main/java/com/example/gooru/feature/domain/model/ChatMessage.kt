package com.example.gooru.feature.domain.model

import com.example.gooru.feature.data.dto.support.chat.Sender

interface ChatMessage {
    var created: String
    val id: Int
    val sender: Sender
    val text: String
}
