package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyComment
import com.example.gooru.feature.data.dto.parser.ParserCommentDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface CommentApi {

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
    const val COMMENT = "comment/"
    const val COMMENT_Id = "comment/{id}/"

}
}