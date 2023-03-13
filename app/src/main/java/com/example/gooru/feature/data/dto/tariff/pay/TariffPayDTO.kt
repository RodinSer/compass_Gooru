package com.example.gooru.feature.data.dto.tariff.pay

import com.google.gson.annotations.SerializedName

class TariffPayDTO(
    @SerializedName("redirect_url")
    val payUri: String
)