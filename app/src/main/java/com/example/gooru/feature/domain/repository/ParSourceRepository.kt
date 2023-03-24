package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.domain.model.body.BodyParSource
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.model.homepage.parsource.PopularParSource

interface ParSourceRepository {

    suspend fun getMyParsingTask(): List<ParSourceHome>

    suspend fun getPopularParsingGroup(): List<PopularParSource>

    suspend fun createNewParSource(body: BodyParSource): ParSourceHome

    suspend fun getHomeParSource(pageSize: Int): List<ParSourceHome>

    suspend fun getParSourceByID(id: Int): ParSourceHome

}