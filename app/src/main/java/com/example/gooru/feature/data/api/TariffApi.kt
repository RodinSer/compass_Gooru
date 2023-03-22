package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.dto.tariff.TariffDto
import com.example.gooru.feature.data.dto.tariff.pay.TariffPayDTO
import com.example.gooru.feature.data.dto.tariff.user.UserTariffDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TariffApi {

    @GET(USER_TARIFF)
    suspend fun getUserTariff(): UserTariffDto

    @GET(ALL_TARIFF)
    suspend fun getTariff(): TariffDto

    @GET(TARIFF_PAY)
    suspend fun payTariff(@Path("type_id") tariffId: Int): TariffPayDTO

    private companion object {
        const val TARIFF_PAY = "api/pay/{type_id}/"
        const val USER_TARIFF = "api/v2/usertariff/"
        const val ALL_TARIFF = "tariff/"
    }
}