package com.example.gooru.feature.domain.useCase.parser

import com.example.gooru.feature.domain.model.parser.ParserInPage

interface GetParserUseCase {

    suspend fun getParser(page: Int, parSourceId: Int?,parserGrope: Int?): ParserInPage
}