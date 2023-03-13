package com.example.gooru.feature.domain.useCase.tariff.impl

import com.example.gooru.feature.domain.repository.TariffRepository
import com.example.gooru.feature.domain.useCase.tariff.PayUseCase

class PayUseCaseImpl(private val tariffRepository: TariffRepository) : PayUseCase {

    override suspend fun getUrl(tariffId: Int) = tariffRepository.getPayTariffUrl(tariffId)
}