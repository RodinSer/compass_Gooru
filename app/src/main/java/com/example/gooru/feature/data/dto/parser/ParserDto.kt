package com.example.gooru.feature.data.dto.parser

import com.example.gooru.feature.domain.model.parser.ParserInPage

class ParserDto(
    private val count: Int,
    private val next: Int?,
    private val previous: Int?,
    private val results: List<ParserResultDto>
) {
    private fun toListParser() = results.map { it.toParser() }
    fun toParserByPage() = ParserInPage(next, previous, toListParser())
}