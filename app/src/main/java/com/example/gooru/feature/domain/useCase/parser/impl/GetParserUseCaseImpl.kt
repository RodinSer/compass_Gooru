package com.example.gooru.feature.domain.useCase.parser.impl

import com.example.gooru.feature.domain.model.parser.ParserInPage
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import com.example.gooru.feature.presentation.tabparsource.parser.parser.ParserGrope

class GetParserUseCaseImpl(private val parserRepository: ParserRepository) : GetParserUseCase {

    override suspend fun getParser(page: Int, parSourceId: Int?, parserGrope: Int?): ParserInPage =
        when (parserGrope) {
            ParserGrope.All.int -> parserRepository.getParserPyParSourceID(page, parSourceId)

            ParserGrope.FAVORITE.int ->
                parserRepository.getFavoriteParserByParSourceID(page, parSourceId)
            ParserGrope.COMMENT.int ->
                parserRepository.getIsCommentParserByParSourceID(page, parSourceId!!)
            else -> throw java.lang.RuntimeException()
        }
}