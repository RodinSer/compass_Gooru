package com.example.gooru.feature.data.dto.user

import com.example.gooru.feature.domain.model.UserAvatar

class AvatarDto (
    val avatar:String
){
    fun toUserAvatar()  = UserAvatar(avatar)
}
