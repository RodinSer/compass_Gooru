package com.example.gooru.feature.data.repositoryImpl

import com.example.gooru.feature.data.api.TariffApi
import com.example.gooru.feature.domain.model.homepage.tariff.Tariff
import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff
import com.example.gooru.feature.domain.repository.TariffRepository

class TariffRepositoryImpl(
    private val tariffApi: TariffApi
) : TariffRepository {

    override suspend fun getAllTariff() = tariffApi.getTariff().toListTariff().sortedBy { it.cost }

    override suspend fun getUserTariff(): UserTariff? = tariffApi.getUserTariff().toUserTariff()

    override suspend fun getPayTariffUrl(tariffId: Int) = tariffApi.payTariff(tariffId).payUri

}