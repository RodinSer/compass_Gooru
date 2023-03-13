package com.example.gooru.feature.domain.useCase.tariff.impl

import com.example.gooru.feature.domain.repository.TariffRepository
import com.example.gooru.feature.domain.useCase.tariff.UserTariffUseCase

class UserTariffUseCaseImpl(
    private val tariffRepository: TariffRepository
) : UserTariffUseCase {
    override suspend fun getUserTariff() = tariffRepository.getUserTariff()
}