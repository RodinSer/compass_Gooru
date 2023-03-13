package com.example.gooru.feature.domain.useCase.parser

import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.model.parser.ParserInPage
import com.example.gooru.feature.domain.repository.ParserRepository

class GetParserUseCaseImpl(private val parserRepository: ParserRepository) : GetParserUseCase {
    override suspend fun getParser(page: Int, parSourceId: Int): ParserInPage {
        return parserRepository.getParserPyParSourceID(page, parSourceId)
    }
}