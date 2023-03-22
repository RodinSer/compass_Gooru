package com.example.gooru.feature.domain.useCase.support.impl

import com.example.gooru.feature.data.body.BodyNewTicket
import com.example.gooru.feature.domain.model.ChatMessage
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.repository.SupportRepository
import com.example.gooru.feature.domain.useCase.support.SupportNewTicketUseCase

class SupportNewTicketUseCaseImpl(
    private val supportRepository: SupportRepository
) : SupportNewTicketUseCase {

    override suspend fun create(name: String, message: String) =
        supportRepository.creteNewTicket(BodyNewTicket(name, message))
}