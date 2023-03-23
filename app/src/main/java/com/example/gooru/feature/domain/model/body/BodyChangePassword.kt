package com.example.gooru.feature.domain.model.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyChangePassword(
    @SerializedName("new_password")
    val newPassword: String,
    @SerializedName("current_password")
    val currentPassword: String
)