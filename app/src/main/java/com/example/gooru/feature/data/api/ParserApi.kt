package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyDownloadFile
import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.body.BodyPatchParser
import com.example.gooru.feature.data.dto.parser.DownloadUrlDto
import com.example.gooru.feature.data.dto.parser.ParserDto
import com.example.gooru.feature.data.dto.parser.ResultFavoriteParserDto
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
        @Query("is_favorite") isFavorite: Boolean = false,
    ): ParserDto

    @GET(PARSER)
    suspend fun getIsCommentParserByParSourceID(
        @Query(PAGE) page: Int,
        @Query(PAR_SOURCE_Id) parSourceId: Int,
        @Query(PAGE_SIZE) pageSize: Int = PAGE_SIZE_INT,
        @Query("is_comment") isComment: Boolean = false,
    ): ParserDto

    @PATCH(PATCH_PARSER)
    suspend fun editParserForApi(
        @Body body: BodyPatchParser,
        @Path("id") parserId: Int
    ): ParserDto

    @POST(FAVORITE)
    suspend fun setFavorite(
        @Body body: BodyFavorite
    )

    @DELETE(FAVORITE_ID)
    suspend fun deleteFavorite(
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET(FAVORITE)
    suspend fun getFavoriteList(): ResultFavoriteParserDto

    @POST(DOWNLOAD_EXCEL)
    suspend fun getUrlDownloadExel(@Body body: BodyDownloadFile): DownloadUrlDto

private companion object {
    const val PARSER = "api/v2/parser/"
    const val PATCH_PARSER = "parser/{id}/"
    const val FAVORITE = "api/v2/usersfavorite/"
    const val FAVORITE_ID = "usersfavorite/{id}/"
    const val DOWNLOAD_EXCEL = "api/v2/parser/download/excel/select/"

    const val PAGE = "page"
    const val PAGE_SIZE = "page_size"
    const val PAGE_SIZE_INT = 10
    const val PAR_SOURCE_Id = "parsource_id"
}

}

