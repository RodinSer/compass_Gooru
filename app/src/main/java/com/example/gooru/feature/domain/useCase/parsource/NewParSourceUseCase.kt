package com.example.gooru.feature.domain.useCase.parsource

import com.example.gooru.core.generation.ExchangeParsing
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome

interface NewParSourceUseCase {

    suspend fun crete(
        dataType: Int,
        description: String,
        freelanceSource: List<ExchangeParsing>,
        keywords: List<String>,
        name: String
    ): ParSourceHome

}