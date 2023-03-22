package com.example.gooru.feature.domain.useCase.parser

interface EditParserUseCasa {

    suspend fun start(text: String, parserId: Int)
}