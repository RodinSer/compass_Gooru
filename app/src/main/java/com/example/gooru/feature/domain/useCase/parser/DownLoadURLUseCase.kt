package com.example.gooru.feature.domain.useCase.parser

interface DownLoadURLUseCase {

    suspend fun getUrl(parSourceId: Int, parserId: Int): String
}