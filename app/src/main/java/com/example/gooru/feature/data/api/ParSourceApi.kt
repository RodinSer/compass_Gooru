package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.dto.parsource.NewParSourceDto
import com.example.gooru.feature.data.dto.parsource.ResponseParSourceDto
import com.example.gooru.feature.domain.model.body.*
import retrofit2.http.*

interface ParSourceApi {

    @GET(PAR_SOURCE)
    suspend fun getUserParSource(): ResponseParSourceDto

    @GET(PAR_SOURCE_POST + ID)
    suspend fun getUserParSourceByID(@Path("id") id: Int): NewParSourceDto

    @GET(PAR_SOURCE)
    suspend fun getUserParSource(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): ResponseParSourceDto

    @POST(PAR_SOURCE_POST)
    suspend fun createParSource(@Body body: BodyParSource): NewParSourceDto

    private companion object {
        const val PAR_SOURCE = "api/v2/parsource_mobile/"
        const val PAR_SOURCE_POST = "api/v2/parsource/"
        const val ID = "{id}/"
    }
}