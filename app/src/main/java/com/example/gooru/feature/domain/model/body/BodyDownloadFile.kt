package com.example.gooru.feature.domain.model.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyDownloadFile(
    @SerializedName("parsource")
    val parSource: Int,
    @SerializedName("parser_ids")
    val parserIds: String?
)