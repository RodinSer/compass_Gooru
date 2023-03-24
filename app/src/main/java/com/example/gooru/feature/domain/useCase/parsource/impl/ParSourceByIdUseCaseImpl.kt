package com.example.gooru.feature.domain.useCase.parsource.impl

import com.example.gooru.feature.domain.repository.ParSourceRepository
import com.example.gooru.feature.domain.useCase.parsource.ParSourceByIdUseCase

class ParSourceByIdUseCaseImpl(private val repository: ParSourceRepository) : ParSourceByIdUseCase {

    override suspend fun getParSource(id: Int) = repository.getParSourceByID(id)
}