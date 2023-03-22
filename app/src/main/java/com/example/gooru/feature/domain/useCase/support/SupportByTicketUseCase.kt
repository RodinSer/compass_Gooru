package com.example.gooru.feature.domain.useCase.support

import com.example.gooru.feature.domain.model.ChatMessage

interface SupportByTicketUseCase {

    suspend fun getMessageByTicketID(ticketId: Int): List<ChatMessage>
}