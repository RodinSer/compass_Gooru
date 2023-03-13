package com.example.gooru.feature.data.dto.user

import com.example.gooru.feature.domain.model.homepage.user.User
import com.google.gson.annotations.SerializedName

class UserDto(
    private val avatar: String,
    private val email: String,
    @SerializedName("first_name")
    private val firstName: String,
    private val groups: List<UsersGroupDto>,
    private val id: Int,
    @SerializedName("is_active")
    private val isActive: Boolean,
    @SerializedName("is_superuser")
    private val isSuperuser: Boolean,
    @SerializedName("last_name")
    private val lastName: String,
    @SerializedName("phone_number")
    private val phoneNumber: String,
    private val role: String,
    private val username: String
) {
    private fun getUserGroups() = groups.map { it.toUsersGroups() }

    fun toUser() = User(
        avatar,
        email,
        firstName,
        getUserGroups(),
        id,
        isActive,
        isSuperuser,
        lastName,
        phoneNumber,
        role,
        username
    )
}