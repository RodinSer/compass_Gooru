package com.example.gooru.feature.domain.useCase.parsource

import com.example.gooru.feature.domain.model.homepage.HomePage

interface HomeParSourceUseCase {

    suspend fun getMyParSource(pageSize:Int): List<HomePage>
}