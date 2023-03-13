package com.example.gooru.feature.data.dto.tariff.user

import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff

class UserTariffDto(
    private val results: List<UserTariffResultDto>
) {
    fun toUserTariff(): UserTariff? {
        var userTariff: UserTariff? = null
        if (results.isNotEmpty()) {
            results.forEach { tariff ->
                if (tariff.checkActiveTariff()) userTariff = tariff.toUserTariff()
            }
        }
        return userTariff
    }

}