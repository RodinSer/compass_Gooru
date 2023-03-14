package com.example.gooru.feature.data.repositoryImpl

import com.example.gooru.core.extensions.simpleDateFormat
import com.example.gooru.feature.data.api.SupportApi
import com.example.gooru.feature.data.body.BodyNewTicket
import com.example.gooru.feature.domain.model.support.SupportTicket
import com.example.gooru.feature.domain.repository.SupportRepository

class SupportRepositoryImpl(
    private val supportApi: SupportApi
) : SupportRepository {

    override suspend fun getAllTickets(page: Int) =
        supportApi.getUserSupports().toListSupportTicket()

    override suspend fun getMessageByTickedId(ticketId: Int) =
        supportApi.getAllMessage(ticketId).results.map { message ->
            message.copy(created = message.created.simpleDateFormat())
        }

    override suspend fun creteNewTicket(bodyNewTicket: BodyNewTicket) =
        supportApi.createNewTicket(bodyNewTicket).toSupport()


}