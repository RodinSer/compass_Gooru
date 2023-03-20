package com.example.gooru.feature.data.dto.parser

import com.example.gooru.feature.domain.model.parser.Parser
import com.google.gson.annotations.SerializedName

class ParserResultDto(
    private val article: String,
    @SerializedName("create_at")
    private val create: String,
    private val id: Int,
    @SerializedName("is_active")
    private val isActive: Boolean,
    @SerializedName("is_public")
    private val isPublic: Boolean,
    @SerializedName("parsource")
    private val parSource: Int,
    @SerializedName("share_url")
    private val shareUrl: String,
    private val title: String,
    private val url: String,
    private val favorite: Int?,
    private val comment: ParserCommentDto?
) {
    fun toParser() =
        Parser(
            article,
            create,
            id,
            isActive,
            isPublic,
            parSource,
            shareUrl,
            title,
            url,
            favorite,
            comment?.comment,
            comment?.id,
            isFavorite = favorite != null
        )
}