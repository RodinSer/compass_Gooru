package com.example.gooru.feature.domain.model.body

@kotlinx.serialization.Serializable
class BodyNewTicket(
    val name: String,
    val message: String
)