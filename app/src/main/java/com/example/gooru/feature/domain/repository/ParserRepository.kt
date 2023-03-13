package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.model.parser.ParserInPage

interface ParserRepository {

    suspend fun getParserPyParSourceID( page: Int,parSourceId: Int):ParserInPage

    suspend fun setFavorite(body: BodyFavorite)

    suspend fun getListFavorite():List<FavoriteParserDto>
}

