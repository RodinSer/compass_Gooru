package com.example.gooru.feature.domain.useCase.parser.impl

import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase

class FavoriteUseCaseImpl(private val repository: ParserRepository) : FavoriteUseCase {
    override suspend fun workFavorite(userId: Int, parserId: Int) {
        repository.setFavorite(BodyFavorite(userId, parserId))
    }
}