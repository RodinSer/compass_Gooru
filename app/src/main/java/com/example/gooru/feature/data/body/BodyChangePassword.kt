package com.example.gooru.feature.data.body

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
class BodyChangePassword(
    @SerializedName("new_password")
    val newPassword: String,
    @SerializedName("current_password")
    val currentPassword: String
)