package com.example.gooru.feature.domain.useCase.support.impl

import com.example.gooru.feature.domain.repository.SupportRepository
import com.example.gooru.feature.domain.useCase.support.SupportByTicketUseCase

class SupportByTicketUseCaseImpl(
    private val supportRepository: SupportRepository
) : SupportByTicketUseCase {
    override suspend fun getMessageByTicketID(ticketId: Int) =
        supportRepository.getMessageByTickedId(ticketId)
}