package com.example.gooru.feature.data.dto.support

import com.example.gooru.core.extensions.simpleDateFormat
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.google.gson.annotations.SerializedName

class SupportTicketDto(
    private val id: Int,
    private val message: String,
    private val parser: Int,
    private val status: String,
    @SerializedName("topic_type")
    private val topicName: String,
    @SerializedName("date_create")
    private val date: String
) {
    fun toSupport() = SupportTicket(id, message, parser, status, topicName, date.simpleDateFormat())
}