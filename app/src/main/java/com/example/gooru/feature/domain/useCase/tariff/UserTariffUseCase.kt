package com.example.gooru.feature.domain.useCase.tariff

import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff

interface UserTariffUseCase {

    suspend fun getUserTariff(): UserTariff?
}