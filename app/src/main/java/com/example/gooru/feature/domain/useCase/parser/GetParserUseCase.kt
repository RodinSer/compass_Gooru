package com.example.gooru.feature.domain.useCase.parser

import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.model.parser.ParserInPage
import com.example.gooru.feature.presentation.parser.parser.ParserGrope

interface GetParserUseCase {
    suspend fun getParser(page: Int, parSourceId: Int?,parserGrope: Int?): ParserInPage
}