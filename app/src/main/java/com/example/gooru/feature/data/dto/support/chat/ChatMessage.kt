package com.example.gooru.feature.data.dto.support.chat

class ChatMessage(
    var created_at: String,
    val id: Int,
    val sender: Sender,
    val text: String,
    val type: String
)