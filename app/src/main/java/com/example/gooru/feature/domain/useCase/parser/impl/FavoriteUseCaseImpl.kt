package com.example.gooru.feature.domain.useCase.parser.impl

import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase

class FavoriteUseCaseImpl(private val repository: ParserRepository) : FavoriteUseCase {

    override suspend fun workFavorite(userId: Int, parser: Parser) =
        if (parser.favoriteId != null) {
            repository.deleteFavoriteById(parser.favoriteId!!)
            null
        } else repository.setFavorite(BodyFavorite(userId, parser.id))

}