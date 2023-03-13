package com.example.gooru.feature.data.dto.tariff.user

import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff
import com.google.gson.annotations.SerializedName

class UserTariffResultDto(
    private val duration: Int,
    @SerializedName("finish_date")
    private val finishDate: String,
    private val created: String,
    @SerializedName("is_active")
    private val isActive: Boolean,
    private val status: String,
    private val tariff: Int,
    private val user: Int
) {
    fun toUserTariff() = UserTariff(created, duration, finishDate, status, tariff)

    fun checkActiveTariff() = isActive
}