package com.example.gooru.feature.data.dto.auth

import com.example.gooru.feature.domain.model.token.UserToken
import com.google.gson.annotations.SerializedName

class TokenDto(
    @SerializedName("auth_token")
   private val authToken: String
){
    fun toUserToken() = UserToken(authToken)
}
