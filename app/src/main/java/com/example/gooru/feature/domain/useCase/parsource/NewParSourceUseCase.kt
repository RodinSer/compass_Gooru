package com.example.gooru.feature.domain.useCase.parsource

import com.example.gooru.feature.presentation.parser.addparsource.ExchangeParsing
import com.google.gson.annotations.SerializedName

interface NewParSourceUseCase {

    suspend fun crete(
        dataType: Int,
        description: String,
        freelanceSource: List<ExchangeParsing>,
        keywords: List<String>,
        name: String
    )

}