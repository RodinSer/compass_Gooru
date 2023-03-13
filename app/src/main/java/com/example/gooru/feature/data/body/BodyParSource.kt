package com.example.gooru.feature.data.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyParSource(
    @SerializedName("data_type")
    val dataType: Int,
    val description: String,
    @SerializedName("freelance_source")
    val freelanceSource: List<Int>,
    val keywords: String,
    val name: String
)