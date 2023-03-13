package com.example.gooru.feature.domain.useCase.support.impl

import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.repository.SupportRepository
import com.example.gooru.feature.domain.useCase.support.SupportAllUseCase

class SupportAllUseCaseImpl(private val repository: SupportRepository): SupportAllUseCase {
    override suspend fun getAllTickets(page: Int) = repository.getAllTickets(page)
}