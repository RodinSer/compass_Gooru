package com.example.gooru.feature.domain.useCase.parser

import com.example.gooru.feature.data.body.BodyPatchParser

interface EditParserUseCasa {

    suspend fun start(text: String,parserId:Int)
}