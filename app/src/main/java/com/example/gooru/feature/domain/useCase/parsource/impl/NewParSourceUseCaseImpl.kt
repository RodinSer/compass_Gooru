package com.example.gooru.feature.domain.useCase.parsource.impl

import com.example.gooru.core.generation.ExchangeParsing
import com.example.gooru.feature.domain.model.body.BodyParSource
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.repository.ParSourceRepository
import com.example.gooru.feature.domain.useCase.parsource.NewParSourceUseCase

class NewParSourceUseCaseImpl(
    private val parSourceRepository: ParSourceRepository
) : NewParSourceUseCase {
    override suspend fun crete(
        dataType: Int,
        description: String,
        freelanceSource: List<ExchangeParsing>,
        keywords: List<String>,
        name: String
    ): ParSourceHome {
        val source = mutableListOf<Int>()
        freelanceSource.forEach {
            if (it.isSelected) source.add(it.id)
        }
        return parSourceRepository.createNewParSource(
            BodyParSource(dataType, description, source, keywords.toString(), name)
        )
    }

}