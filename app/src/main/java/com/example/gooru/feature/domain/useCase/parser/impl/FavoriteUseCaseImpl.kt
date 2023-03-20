package com.example.gooru.feature.domain.useCase.parser.impl


import android.util.Log
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase

class FavoriteUseCaseImpl(private val repository: ParserRepository) : FavoriteUseCase {

    override suspend fun workFavorite(userId: Int, parser: Parser): Int? {
        Log.e("isFavorite", " ${parser.favoriteId}")

        return if (parser.favoriteId != null) {
            Log.e("isFavorite", " delete")
            repository.deleteFavoriteById(parser.favoriteId!!)
            null
        } else {
            Log.e("isFavorite", " set")
            repository.setFavorite(BodyFavorite(userId, parser.id))
        }

    }
}
