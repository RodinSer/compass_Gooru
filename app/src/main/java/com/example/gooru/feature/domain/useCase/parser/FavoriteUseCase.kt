package com.example.gooru.feature.domain.useCase.parser

import com.example.gooru.feature.domain.model.parser.Parser

interface FavoriteUseCase {

    suspend fun workFavorite(userId: Int, parser: Parser): Int?
}