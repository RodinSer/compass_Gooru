package com.example.gooru.feature.data.dto.support.chat

import com.example.gooru.feature.domain.model.ChatMessage
import com.google.gson.annotations.SerializedName

data class ChatMessageDto(
    @SerializedName("created_at")
    override var created: String,
    override val id: Int,
    override val sender: Sender,
    override val text: String,
) : ChatMessage

