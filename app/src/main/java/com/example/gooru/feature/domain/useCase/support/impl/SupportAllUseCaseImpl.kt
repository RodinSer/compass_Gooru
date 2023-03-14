package com.example.gooru.feature.domain.useCase.support.impl

import com.example.gooru.feature.domain.repository.SupportRepository
import com.example.gooru.feature.domain.useCase.support.SupportAllTicketsUseCase

class SupportAllUseCaseImpl(private val repository: SupportRepository): SupportAllTicketsUseCase {
    override suspend fun getAllTickets(page: Int) = repository.getAllTickets(page)
}