package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.data.body.BodyDownloadFile
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.body.BodyPatchParser
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.domain.model.parser.ParserInPage

interface ParserRepository {

    suspend fun getParserPyParSourceID( page: Int,parSourceId: Int?):ParserInPage

    suspend fun getFavoriteParserByParSourceID(page: Int, parSourceId: Int?, ): ParserInPage

    suspend fun getIsCommentParserByParSourceID(page: Int, parSourceId: Int, ): ParserInPage

    suspend fun setFavorite(body: BodyFavorite)

    suspend fun getListFavorite():List<FavoriteParserDto>

    suspend fun getUrlExel(body: BodyDownloadFile): String

    suspend fun deleteFavoriteById(favoriteId:Int)

    suspend fun editParser(bodyPatchParser: BodyPatchParser,parserId:Int)
}

