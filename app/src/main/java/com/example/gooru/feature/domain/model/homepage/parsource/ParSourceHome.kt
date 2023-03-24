package com.example.gooru.feature.domain.model.homepage.parsource

import android.os.Parcelable
import com.example.gooru.feature.domain.model.homepage.HomePage
import kotlinx.parcelize.Parcelize

@Parcelize
class ParSourceHome(
    val id: Int,
    val lastTimeSync: String,
    val name: String,
    val condition: String,
    val keywords: String
) : HomePage, Parcelable
