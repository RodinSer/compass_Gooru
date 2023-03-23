package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.dto.parser.ParserCommentDto
import com.example.gooru.feature.domain.model.body.BodyComment
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CommentApi {

    @POST(COMMENT)
    suspend fun postComment(@Body body: BodyComment): ParserCommentDto

    @PATCH(COMMENT+ COMMENT_Id)
    suspend fun patchComment(
        @Body body: BodyComment,
        @Path("id") commentId: Int
    ): Response<ResponseBody>

    @DELETE(COMMENT+ COMMENT_Id)
    suspend fun deleteComment(
        @Path("id") commentId: Int
    ): Response<ResponseBody>

    private companion object{
        const val COMMENT = "api/v2/comment/"
        const val COMMENT_Id = "{id}/"
    }
}