package com.example.gooru.feature.domain.useCase.tariff

import com.example.gooru.feature.domain.model.homepage.tariff.Tariff

interface AllTariffUseCase {

    suspend fun getAllTariff(): List<Tariff>
}