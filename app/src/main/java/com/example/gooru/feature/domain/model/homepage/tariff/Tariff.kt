package com.example.gooru.feature.domain.model.homepage.tariff

import com.example.gooru.feature.domain.model.homepage.HomePage

data class Tariff(
    val cost: Int,
    val description: List<String>,
    val id: Int,
    val name: String
) : HomePage