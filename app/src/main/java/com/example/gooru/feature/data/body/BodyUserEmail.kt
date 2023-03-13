package com.example.gooru.feature.data.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyUserEmail(
    @SerializedName("email")
    val email: String
)