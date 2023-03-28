package com.example.gooru.feature.domain.model.body

@kotlinx.serialization.Serializable
class BodyNewTicket(
    val topic_type: String,
    val message: String
)