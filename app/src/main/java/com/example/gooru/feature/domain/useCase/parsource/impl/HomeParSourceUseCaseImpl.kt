package com.example.gooru.feature.domain.useCase.parsource.impl

import com.example.gooru.feature.domain.model.homepage.NewParSource
import com.example.gooru.feature.domain.model.homepage.HomePage
import com.example.gooru.feature.domain.model.homepage.NextButton
import com.example.gooru.feature.domain.repository.ParSourceRepository
import com.example.gooru.feature.domain.useCase.parsource.HomeParSourceUseCase

class HomeParSourceUseCaseImpl(
    private val repository: ParSourceRepository,
) : HomeParSourceUseCase {

    override suspend fun getMyParSource(pageSize:Int): List<HomePage> {
        val myParsing: MutableList<HomePage> = mutableListOf(NewParSource)
        myParsing.addAll(repository.getHomeParSource(pageSize))
        if (myParsing.size > pageSize)
            myParsing.add(NextButton)
        return myParsing
    }
}