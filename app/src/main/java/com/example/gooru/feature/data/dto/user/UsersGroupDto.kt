package com.example.gooru.feature.data.dto.user

import com.example.gooru.feature.domain.model.homepage.user.UserGroups

class UsersGroupDto(
    val id: Int,
    val name: String
) {

    fun toUsersGroups() = UserGroups(id, name)
}