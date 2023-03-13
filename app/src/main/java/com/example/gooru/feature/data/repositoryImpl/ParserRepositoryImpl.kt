package com.example.gooru.feature.data.repositoryImpl

import com.example.gooru.feature.data.api.ParSourceApi
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.domain.repository.ParserRepository

class ParserRepositoryImpl(private val parserApi: ParSourceApi) : ParserRepository {

    override suspend fun getParserPyParSourceID(page: Int, parSourceId: Int) =
        parserApi.getParserForParSourceId(page, parSourceId).toParserByPage()

    override suspend fun setFavorite(body: BodyFavorite) {
        parserApi.setFavorite(body)
    }

    override suspend fun getListFavorite(): List<FavoriteParserDto> =
        parserApi.getFavoriteList().results

}