package com.example.gooru.feature.data.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyDownloadFile(
    @SerializedName("parsource")
    val parSource: Int,
    @SerializedName("parser_ids")
    val parserIds: String
)