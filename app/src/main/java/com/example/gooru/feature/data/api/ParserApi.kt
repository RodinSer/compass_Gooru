package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.dto.IdFavoriteDto
import com.example.gooru.feature.data.dto.parser.DownloadUrlDto
import com.example.gooru.feature.data.dto.parser.ParserDto
import com.example.gooru.feature.data.dto.parser.ResultFavoriteParserDto
import com.example.gooru.feature.domain.model.body.BodyDownloadFile
import com.example.gooru.feature.domain.model.body.BodyFavorite
import com.example.gooru.feature.domain.model.body.BodyPatchParser
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ParserApi {

    @GET(PARSER)
    suspend fun getParserForParSourceId(
        @Query(PAGE) page: Int,
        @Query(PAR_SOURCE_Id) parSourceId: Int?,
        @Query(PAGE_SIZE) pageSize: Int = PAGE_SIZE_INT,
    ): ParserDto

    @GET(PARSER)
    suspend fun getFavoriteParserByParSourceID(
        @Query(PAGE) page: Int,
        @Query(PAR_SOURCE_Id) parSourceId: Int?,
        @Query(PAGE_SIZE) pageSize: Int = PAGE_SIZE_INT,
        @Query(IS_FAVORITE) isFavorite: Boolean = false,
    ): ParserDto

    @GET(PARSER)
    suspend fun getIsCommentParserByParSourceID(
        @Query(PAGE) page: Int,
        @Query(PAR_SOURCE_Id) parSourceId: Int,
        @Query(PAGE_SIZE) pageSize: Int = PAGE_SIZE_INT,
        @Query(IS_COMMENT) isComment: Boolean = false,
    ): ParserDto

    @PATCH(PATCH_PARSER)
    suspend fun editParserForApi(
        @Body body: BodyPatchParser,
        @Path(ID) parserId: Int,
    ): ParserDto

    @POST(FAVORITE_ALL)
    suspend fun setFavorite(
        @Body body: BodyFavorite,
    ): IdFavoriteDto

    @DELETE(FAVORITE_ID)
    suspend fun deleteFavorite(
        @Path(ID) id: Int,
    ): Response<ResponseBody>

    @GET(FAVORITE_ALL)
    suspend fun getFavoriteList(): ResultFavoriteParserDto

    @POST(DOWNLOAD_EXCEL)
    suspend fun getUrlDownloadExel(@Body body: BodyDownloadFile): DownloadUrlDto

    private companion object {
        const val PARSER = "api/v2/parser/"
        const val PATCH_PARSER = "api/v2/parser/{id}/"
        const val FAVORITE_ALL = "api/v2/usersfavorite/"
        const val FAVORITE_ID = "api/v2/usersfavorite/{id}/"
        const val DOWNLOAD_EXCEL = "api/v2/parser/download/excel/select/"

        const val PAGE = "page"
        const val PAGE_SIZE = "page_size"
        const val PAGE_SIZE_INT = 10
        const val PAR_SOURCE_Id = "parsource_id"
        const val ID = "id"
        const val IS_FAVORITE = "is_favorite"
        const val IS_COMMENT = "is_comment"

    }
}