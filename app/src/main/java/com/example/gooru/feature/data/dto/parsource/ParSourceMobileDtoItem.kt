package com.example.gooru.feature.data.dto.parsource


import com.example.gooru.core.extensions.simpleDateFormat
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.google.gson.annotations.SerializedName
import java.util.*

class ParSourceMobileDtoItem(
    val id: Int,
    @SerializedName("last_time_sync")
    private val lastTimeSync: String,
    private val name: String,
    val condition: String,
    val keywords: String,
    val parsers_count: Int
) {

    fun toParSourceHome() = ParSourceHome(
         id, lastTimeSync.simpleDateFormat(), name
    )


}