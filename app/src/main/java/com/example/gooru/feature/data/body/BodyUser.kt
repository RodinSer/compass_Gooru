package com.example.gooru.feature.data.body

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class BodyUser(
    @SerializedName("username")
    val email: String,
    @SerializedName("password")
    val password: String? = null,
)