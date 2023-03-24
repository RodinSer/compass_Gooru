package com.example.gooru.feature.data.dto.tariff

import com.example.gooru.feature.domain.model.homepage.tariff.Tariff

class TariffResultDto(
    private val cost: Int,
    private val description: String,
    private val group: Int,
    private val id:Int,
    private val name: String
) {
    fun toTariff() = Tariff(cost, description.split(";"), id, name)
}