package com.example.gooru.feature.domain.useCase.tariff

interface PayUseCase {

    suspend fun getUrl(tariffId:Int):String
}