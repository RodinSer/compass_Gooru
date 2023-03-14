package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.body.BodyParSource
import com.example.gooru.feature.data.dto.parser.ParserDto
import com.example.gooru.feature.data.dto.parser.ResultFavoriteParserDto
import com.example.gooru.feature.data.dto.parser.d.DownloadUrlDto
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
        @Query(PAR_SOURCE_Id) parSourceId: Int,
        @Query(PAGE_SIZE) pageSize: Int = PAGE_SIZE_INT,
    ): ParserDto

    @POST(FAVORITE_ALL)
    suspend fun setFavorite(
        @Body body: BodyFavorite
    )

    @DELETE(FAVORITE_ID)
    suspend fun deleteFavorite(
        @Path("id") id: Int
    )

    @GET(FAVORITE_ALL)
    suspend fun getFavoriteList(): ResultFavoriteParserDto

    @POST(DOWNLOAD_EXCEL)
    suspend fun getUrlDownloadExel(/*@Body body: BodyDownloadFile*/): DownloadUrlDto

    private companion object {
        const val PAR_SOURCE = "api/v2/parsource_mobile/"
        const val PAR_SOURCE_POST = "api/v2/parsource/"
        const val PARSER = "parser/"
        const val FAVORITE_ALL = "usersfavorite/"
        const val FAVORITE_ID = "usersfavorite/{id}"
        const val DOWNLOAD_EXCEL = "api/v2/parser/download/excel/select/"

        const val PAGE = "page"
        const val PAGE_SIZE = "page_size"
        const val PAGE_SIZE_INT = 10
        const val PAR_SOURCE_Id = "parsource_id"
    }
}