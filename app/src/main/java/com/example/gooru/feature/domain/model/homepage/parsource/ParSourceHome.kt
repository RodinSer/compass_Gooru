package com.example.gooru.feature.domain.model.homepage.parsource

import com.example.gooru.feature.domain.model.homepage.HomePage
import com.google.gson.annotations.SerializedName

class ParSourceHome(
    val condition: String,
    val date: String,
    val id: Int,
    val lastTimeSync: String,
    val name: String
) : HomePage