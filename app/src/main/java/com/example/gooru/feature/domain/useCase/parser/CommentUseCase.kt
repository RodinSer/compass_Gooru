package com.example.gooru.feature.domain.useCase.parser

interface CommentUseCase {

    suspend fun doWork(parserId: Int, comment: String?, commentId: Int?): Int?
}