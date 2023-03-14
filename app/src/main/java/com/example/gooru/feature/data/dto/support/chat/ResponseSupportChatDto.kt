package com.example.gooru.feature.data.dto.support.chat

class ResponseSupportChatDto(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ChatMessageDto>
)