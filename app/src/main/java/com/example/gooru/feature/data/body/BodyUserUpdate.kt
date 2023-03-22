package com.example.gooru.feature.data.body

import com.google.gson.annotations.SerializedName

class BodyUserUpdate(
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
)