package com.example.gooru.feature.domain.model.parser

data class Parser(
    var article: String,
    val create: String,
    var id: Int,
    val isActive: Boolean,
    val isPublic: Boolean,
    val parSource: Int,
    val shareUrl: String,
    val title: String,
    val url: String,
    var favoriteId: Int?,
    var comment: String?,
    val commentId: Int?,

    var maxTextLine: Int = 3,
    var isCommentVisibility: Boolean = false,
    var isFavorite: Boolean = false,
    var isEditArticle: Boolean = false
) {

}