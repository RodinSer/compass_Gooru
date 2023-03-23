package com.example.gooru.feature.domain.useCase.parser.impl

import com.example.gooru.feature.domain.model.body.BodyPatchParser
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa

class EditParserUseCasaImpl(
    private val repository: ParserRepository
) : EditParserUseCasa {

    override suspend fun start(text: String, parserId: Int) {
        repository.editParser(BodyPatchParser(text), parserId)
    }

}