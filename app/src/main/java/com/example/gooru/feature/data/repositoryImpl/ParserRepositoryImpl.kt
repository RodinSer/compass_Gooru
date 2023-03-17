package com.example.gooru.feature.data.repositoryImpl

import android.util.Log
import com.example.gooru.feature.data.api.ParSourceApi
import com.example.gooru.feature.data.body.BodyDownloadFile
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.body.BodyPatchParser
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.domain.model.parser.ParserInPage
import com.example.gooru.feature.domain.repository.ParserRepository
import retrofit2.Response

class ParserRepositoryImpl(private val parserApi: ParSourceApi) : ParserRepository {

    override suspend fun getParserPyParSourceID(page: Int, parSourceId: Int?) =
        parserApi.getParserForParSourceId(page, parSourceId).toParserByPage()


    override suspend fun getFavoriteParserByParSourceID(page: Int, parSourceId: Int?) =
        parserApi.getFavoriteParserByParSourceID(page, parSourceId).toParserByPage()

    override suspend fun getIsCommentParserByParSourceID(page: Int, parSourceId: Int) =
        parserApi.getIsCommentParserByParSourceID(page, parSourceId).toParserByPage()

    override suspend fun getListFavorite(): List<FavoriteParserDto> =
        parserApi.getFavoriteList().results

    override suspend fun getUrlExel(body: BodyDownloadFile) =
        parserApi.getUrlDownloadExel(body).URL

    override suspend fun deleteFavoriteById(favoriteId: Int) {
        parserApi.deleteFavorite(favoriteId)
    }

    override suspend fun setFavorite(body: BodyFavorite) {
        parserApi.setFavorite(body)
    }

    override suspend fun editParser(bodyPatchParser: BodyPatchParser, parserId: Int) {
        parserApi.editParserForApi(bodyPatchParser, parserId)
    }


}