package com.example.gooru.feature.data.repositoryImpl

import com.example.gooru.feature.data.api.ParSourceApi
import com.example.gooru.feature.data.body.BodyComment
import com.example.gooru.feature.domain.repository.CommentRepository

class CommentRepositoryImpl(private val commentApi: ParSourceApi) : CommentRepository {

    override suspend fun setComment(body: BodyComment) = commentApi.postComment(body).id

    override suspend fun deleteComment(commentID: Int) {
        commentApi.deleteComment(commentID)
    }

    override suspend fun patchComment(body: BodyComment,commentID: Int) {
        commentApi.patchComment(body,commentID)
    }
}