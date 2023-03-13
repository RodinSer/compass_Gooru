package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyFavorite
import com.example.gooru.feature.data.body.BodyParSource
import com.example.gooru.feature.data.dto.parser.ParserDto
import com.example.gooru.feature.data.dto.parser.ResultFavoriteParserDto
import com.example.gooru.feature.data.dto.parsource.NewParSourceDto
import com.example.gooru.feature.data.dto.parsource.ResponseParSourceDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ParSourceApi {

    @GET(PAR_SOURCE)
    suspend fun getUserParSource(): ResponseParSourceDto

    @POST(PAR_SOURCE_POST)
    suspend fun createParSource(@Body body:BodyParSource): NewParSourceDto

    @GET(PARSER)
    suspend fun getParserForParSourceId(
        @Query(PAGE) page: Int,
        @Query(PAR_SOURCE_Id) parSourceId: Int,
        @Query(PAGE_SIZE) pageSize: Int = PAGE_SIZE_INT,
    ): ParserDto

    @POST("usersfavorite/")
    suspend fun setFavorite(
        @Body body: BodyFavorite
    )


    @DELETE("usersfavorite/{id}")
    suspend fun deleteFavorite(
        @Path("id") id:Int
    )

    @GET("usersfavorite/")
    suspend fun getFavoriteList():ResultFavoriteParserDto

    companion object {
        private const val PAR_SOURCE = "parsource_mobile/"
        private const val PAR_SOURCE_POST = "parsource/"
        private const val PARSER = "parser/"
        private const val PAGE = "page"
        private const val PAGE_SIZE = "page_size"
        private const val PAGE_SIZE_INT = 10
        private const val PAR_SOURCE_Id = "parsource_id"
    }
}