package com.example.gooru.feature.domain.useCase.parsource.impl

import com.example.gooru.feature.domain.model.homepage.NewParSource
import com.example.gooru.feature.domain.model.homepage.HomePage
import com.example.gooru.feature.domain.repository.ParSourceRepository
import com.example.gooru.feature.domain.useCase.parsource.ParSourceUseCase

class ParSourceUseCaseImpl(
    private val repository: ParSourceRepository,
) : ParSourceUseCase {

    override suspend fun getMyParSource(): List<HomePage> {
        val myParsing: MutableList<HomePage> = mutableListOf(NewParSource())
        myParsing.addAll(repository.getMyParsingTask())
        return myParsing
    }
}