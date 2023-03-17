package com.example.gooru.feature.domain.useCase.parser.impl

import com.example.gooru.feature.data.body.BodyComment
import com.example.gooru.feature.domain.repository.CommentRepository
import com.example.gooru.feature.domain.useCase.parser.CommentUseCase

class CommentUseCaseImpl(private val commentRepository: CommentRepository) : CommentUseCase {

    override suspend fun doWork(parserId: Int, comment: String?, commentId: Int?): Int? {
        val body = BodyComment(parserId, comment)
        var newId:Int? = null
        if (commentId == null)  newId = commentRepository.setComment(body)
        else if (comment == "") commentRepository.deleteComment(commentId)
        else commentRepository.patchComment(body, commentId)

        return newId
    }

}