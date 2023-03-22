package com.example.gooru.feature.domain.model.homepage.parsource

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.example.gooru.feature.domain.model.homepage.HomePage
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class ParSourceHome(
    val id: Int,
    val lastTimeSync: String,
    val name: String
) : HomePage,Parcelable
