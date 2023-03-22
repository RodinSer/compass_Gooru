package com.example.gooru.feature.domain.useCase.parsource

import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.presentation.tabparsource.parser.addparsource.ExchangeParsing

interface NewParSourceUseCase {

    suspend fun crete(
        dataType: Int,
        description: String,
        freelanceSource: List<ExchangeParsing>,
        keywords: List<String>,
        name: String
    ): ParSourceHome

}