package com.example.gooru.feature.domain.model.parser

data class Parser(
    var article: String,
    val create: String,
    val id: Int,
    val isActive: Boolean,
    val isPublic: Boolean,
    val parSource: Int,
    val shareUrl: String,
    val title: String,
    val url: String,
    var favoriteId: Int? = null
)