package com.example.gooru.feature.domain.model.body

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class BodyRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)