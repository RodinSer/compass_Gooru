package com.example.gooru.feature.domain.useCase.parser

interface FavoriteUseCase {

    suspend fun workFavorite(userId:Int,parserId:Int)
}