package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.*
import com.example.gooru.feature.data.dto.IdFavoriteDto
import com.example.gooru.feature.data.dto.parser.DownloadUrlDto
import com.example.gooru.feature.data.dto.parser.ParserCommentDto
import com.example.gooru.feature.data.dto.parser.ParserDto
import com.example.gooru.feature.data.dto.parser.ResultFavoriteParserDto
import com.example.gooru.feature.data.dto.parsource.NewParSourceDto
import com.example.gooru.feature.data.dto.parsource.ResponseParSourceDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ParSourceApi {

    @GET(PAR_SOURCE)
    suspend fun getUserParSource(): ResponseParSourceDto

    @POST(PAR_SOURCE_POST)
    suspend fun createParSource(@Body body: BodyParSource): NewParSourceDto

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

    @POST(FAVORITE_ALL)
    suspend fun setFavorite(
        @Body body: BodyFavorite
    ): IdFavoriteDto

    @DELETE(FAVORITE_ID)
    suspend fun deleteFavorite(
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET(FAVORITE_ALL)
    suspend fun getFavoriteList(): ResultFavoriteParserDto

    @POST(DOWNLOAD_EXCEL)
    suspend fun getUrlDownloadExel(@Body body: BodyDownloadFile): DownloadUrlDto

    @POST(COMMENT)
    suspend fun postComment(@Body body: BodyComment): ParserCommentDto

    @PATCH(COMMENT_Id)
    suspend fun patchComment(
        @Body body: BodyComment,
        @Path("id") commentId: Int
    ): Response<ResponseBody>

    @DELETE(COMMENT_Id)
    suspend fun deleteComment(
        @Path("id") commentId: Int
    ): Response<ResponseBody>


    private companion object {
        const val PAR_SOURCE = "api/v2/parsource_mobile/"
        const val PAR_SOURCE_POST = "api/v2/parsource/"
        const val PARSER = "api/v2/parser/"
        const val PATCH_PARSER = "parser/{id}/"
        const val FAVORITE_ALL = "api/v2/usersfavorite/"
        const val FAVORITE_ID = "usersfavorite/{id}/"
        const val DOWNLOAD_EXCEL = "api/v2/parser/download/excel/select/"
        const val COMMENT = "api/v2/comment/"
        const val COMMENT_Id = "api/v2/comment/{id}/"

        const val PAGE = "page"
        const val PAGE_SIZE = "page_size"
        const val PAGE_SIZE_INT = 10
        const val PAR_SOURCE_Id = "parsource_id"
    }
}