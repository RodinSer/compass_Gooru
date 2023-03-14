package com.example.gooru.feature.domain.useCase.parser.impl

import com.example.gooru.feature.domain.model.parser.ParserInPage
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase

class GetParserUseCaseImpl(private val parserRepository: ParserRepository) : GetParserUseCase {
    override suspend fun getParser(page: Int, parSourceId: Int): ParserInPage {
        return parserRepository.getParserPyParSourceID(page, parSourceId)
    }
}