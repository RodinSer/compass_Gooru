package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.dto.support.ResponseSupportDto
import com.example.gooru.feature.domain.model.parser.ParserInPage
import retrofit2.http.GET
import retrofit2.http.Query

interface SupportApi {
    @GET(USER_SUPPORT)
    suspend fun getUserSupports(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int = 10
    ): ResponseSupportDto

    private companion object {
        const val USER_SUPPORT = "users/support/"
    }

}