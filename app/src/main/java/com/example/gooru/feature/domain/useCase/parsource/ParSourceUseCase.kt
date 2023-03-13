package com.example.gooru.feature.domain.useCase.parsource

import com.example.gooru.feature.domain.model.homepage.HomePage

interface ParSourceUseCase {

    suspend fun getMyParSource(): List<HomePage>
}