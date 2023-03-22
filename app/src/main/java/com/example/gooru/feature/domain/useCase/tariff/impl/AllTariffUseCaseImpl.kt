package com.example.gooru.feature.domain.useCase.tariff.impl

import com.example.gooru.feature.domain.model.homepage.tariff.Tariff
import com.example.gooru.feature.domain.repository.TariffRepository
import com.example.gooru.feature.domain.useCase.tariff.AllTariffUseCase

class AllTariffUseCaseImpl(private val tariffRepository: TariffRepository) : AllTariffUseCase {

    override suspend fun getAllTariff(): List<Tariff> = tariffRepository.getAllTariff()
}