package com.example.gooru.feature.data.dto.tariff

class TariffDto(
    private val count: Int,
    private val next: String?,
    private val previous: String?,
    private val results: List<TariffResultDto>
) {
    fun toListTariff() = results.map { dto -> dto.toTariff() }

}
