package com.example.gooru.feature.domain.useCase.support

import com.example.gooru.feature.domain.model.support.SupportTicket

interface SupportNewTicketUseCase {
    suspend fun create(name: String, message: String): SupportTicket
}