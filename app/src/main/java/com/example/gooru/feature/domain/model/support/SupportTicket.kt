package com.example.gooru.feature.domain.model.support

data class SupportTicket(
    val id: Int,
    val message: String,
    val parser: Int,
    val status: String,
    val topicName: String,
    val date: String
)