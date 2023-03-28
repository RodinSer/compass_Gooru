package com.example.gooru.feature.domain.useCase.support.impl

import com.example.gooru.feature.domain.model.body.BodyNewTicket
import com.example.gooru.feature.domain.repository.SupportRepository
import com.example.gooru.feature.domain.useCase.support.SupportNewTicketUseCase

class SupportNewTicketUseCaseImpl(
    private val supportRepository: SupportRepository
) : SupportNewTicketUseCase {

    override suspend fun create(themeId: Int, message: String) =
        supportRepository.creteNewTicket(BodyNewTicket(themeId.toString(), message))
}