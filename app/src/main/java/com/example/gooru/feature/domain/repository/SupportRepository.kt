package com.example.gooru.feature.domain.repository

import com.example.gooru.feature.domain.model.support.SupportTicket

interface SupportRepository {

    suspend fun getAllTickets(page:Int):List<SupportTicket>
}