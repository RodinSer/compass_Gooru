package com.example.gooru.feature.data.dto.parsource

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import com.example.gooru.core.extensions.simpleDateFormat
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.google.gson.annotations.SerializedName
import java.util.*

class ParSourceMobileDtoItem(
    private val condition: String,
    private val date: String,
    val id: Int,
    @SerializedName("last_time_sync")
    private val lastTimeSync: String,
    private val name: String
) {

    fun toParSourceHome() = ParSourceHome(
        condition, date, id, lastTimeSync.simpleDateFormat(), name
    )


}