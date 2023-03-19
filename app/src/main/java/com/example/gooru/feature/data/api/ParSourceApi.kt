package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.*
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




    private companion object {
        const val PAR_SOURCE = "api/v2/parsource_mobile/"
        const val PAR_SOURCE_POST = "api/v2/parsource/"
    }
}