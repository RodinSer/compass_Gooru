package com.example.gooru.feature.domain.useCase.parser.impl

import android.util.Log
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase

class FavoriteUseCaseImpl(private val repository: ParserRepository) : FavoriteUseCase {

    override suspend fun workFavorite(userId: Int, parser: Parser) {
        Log.e("Kart","set ${parser.favoriteId}")
        if (parser.favoriteId == null) {
            Log.e("Kart","set")
            repository.setFavorite(BodyFavorite(userId, parser.id))
        }
        else {
            Log.e("Kart","delete")
            repository.deleteFavoriteById(parser.favoriteId!!)
        }
    }
}