package com.example.gooru.feature.domain.useCase.parser.impl

import android.util.Log
import com.example.gooru.feature.data.body.BodyPatchParser
import com.example.gooru.feature.domain.repository.ParserRepository
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa

class EditParserUseCasaImpl(
    private val repository: ParserRepository
) : EditParserUseCasa {
    override suspend fun start(text: String, parserId: Int) {
        Log.e("Kart","$text")
        repository.editParser(BodyPatchParser("ВАСЯ"), parserId)

    }

}