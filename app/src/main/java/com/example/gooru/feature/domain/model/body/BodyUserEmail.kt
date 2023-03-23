package com.example.gooru.feature.domain.model.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyUserEmail(
    @SerializedName("email")
    val email: String
)