package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.domain.model.body.BodyNewTicket
import com.example.gooru.feature.domain.model.ChatMessage
import com.example.gooru.feature.domain.model.support.SupportTicket

interface SupportRepository {

    suspend fun getAllTickets(page:Int):List<SupportTicket>

    suspend fun getMessageByTickedId(ticketId:Int):List<ChatMessage>

    suspend fun creteNewTicket(bodyNewTicket: BodyNewTicket):SupportTicket
}