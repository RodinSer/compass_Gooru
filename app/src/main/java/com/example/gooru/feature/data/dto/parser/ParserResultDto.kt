package com.example.gooru.feature.data.dto.parser

import com.example.gooru.feature.domain.model.parser.Parser
import com.google.gson.annotations.SerializedName

class ParserResultDto(
    val article: String,
    @SerializedName("create_at")
    val create: String,
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_public")
    val isPublic: Boolean,
    @SerializedName("parsource")
    val parSource: Int,
    @SerializedName("share_url")
    val shareUrl: String,
    val title: String,
    val url: String
){
    fun toParser() = Parser(article, create, id, isActive, isPublic, parSource, shareUrl, title, url)
}