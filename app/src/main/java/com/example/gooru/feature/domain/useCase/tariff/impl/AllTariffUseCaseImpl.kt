package com.example.gooru.feature.domain.useCase.tariff.impl

import android.util.Log
import com.example.gooru.feature.domain.model.homepage.tariff.Tariff
import com.example.gooru.feature.domain.repository.TariffRepository
import com.example.gooru.feature.domain.useCase.tariff.AllTariffUseCase

class AllTariffUseCaseImpl(private val tariffRepository: TariffRepository) : AllTariffUseCase {

    override suspend fun getAllTariff(): List<Tariff> {

        val tariff = tariffRepository.getAllTariff()
        Log.d("Kart", tariff.toString())

        return tariffRepository.getAllTariff()
    }
}