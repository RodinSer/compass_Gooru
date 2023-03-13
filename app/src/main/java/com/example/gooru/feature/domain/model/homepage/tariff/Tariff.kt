package com.example.gooru.feature.domain.model.homepage.tariff

import com.example.gooru.feature.domain.model.homepage.HomePage

class Tariff(
    val cost: Int,
    val description: List<String>,
    val group: Int,
    val id: Int,
    val name: String
):HomePage