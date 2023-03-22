package com.example.gooru.feature.data.dto.tariff.user

import com.example.gooru.core.extensions.simpleDateFormat
import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff
import com.google.gson.annotations.SerializedName

class UserTariffResultDto(
    private val created: String,
    @SerializedName("finish_date")
    private val finishDate: String,
    @SerializedName("is_active")
    private val isActive: Boolean,
    @SerializedName("tariff_description")
    private val tariffDescription: String,
    @SerializedName("tariff_name")
    private val tariffName: String,
) {

    fun toUserTariff() = UserTariff(
        created.simpleDateFormat(),
        finishDate.simpleDateFormat(),
        tariffDescription.split(";"),
        tariffName
    )

    fun checkActiveTariff() = isActive
}