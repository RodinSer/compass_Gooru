package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.domain.model.body.BodyComment

interface CommentRepository {

   suspend fun setComment(body: BodyComment):Int

   suspend fun deleteComment(commentID:Int)

   suspend fun patchComment(body:BodyComment,commentID: Int)

}