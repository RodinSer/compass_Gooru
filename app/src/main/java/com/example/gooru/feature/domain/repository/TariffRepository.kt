package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.data.dto.tariff.pay.TariffPayDTO
import com.example.gooru.feature.domain.model.homepage.tariff.Tariff
import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff

interface TariffRepository {

    suspend fun getAllTariff(): List<Tariff>

    suspend fun getUserTariff(): UserTariff?

    suspend fun getPayTariffUrl(tariffId:Int):String
}