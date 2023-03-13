package com.example.gooru.feature.data.home

import com.example.gooru.feature.domain.model.homepage.HomeInfo
import com.example.gooru.feature.domain.model.homepage.HomePage

class ListHorizontal (
    val nameCategory:String,
    val list: List<HomePage>
): HomeInfo
