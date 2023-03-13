package com.example.gooru.feature.domain.model.support

data class SupportTicket(
    val email: String,
    val id: Int,
    val message: String,
    val name: String,
    val parser: Int,
    val phoneNumber: String,
    val status: Int,
    val topicType: Int
)