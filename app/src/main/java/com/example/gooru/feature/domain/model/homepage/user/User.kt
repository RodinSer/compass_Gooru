package com.example.gooru.feature.domain.model.homepage.user

import com.example.gooru.feature.data.body.BodyUserUpdate
import com.example.gooru.feature.domain.model.homepage.HomePage
import com.example.gooru.feature.domain.model.homepage.tariff.UserTariff

class User(
    val avatar: String?,
    val email: String,
    val firstName: String,
    val groups:List<UserGroups>,
    val id: Int,
    val isActive: Boolean,
    val isSuperuser: Boolean,
    val lastName: String,
    val phoneNumber: String,
    val role: String,
    val username: String,
    var tariff:UserTariff? = null
):HomePage{
    fun toBody(first: String, last: String?) = if (last!=null)
        BodyUserUpdate(null, first, last, null)
    else  BodyUserUpdate(null, null, null, first)
}