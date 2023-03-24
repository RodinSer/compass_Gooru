package com.example.gooru.feature.domain.useCase.parsource

import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome

interface ParSourceByIdUseCase {

    suspend fun getParSource(id:Int):ParSourceHome
}