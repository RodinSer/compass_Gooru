package com.example.gooru.feature.data.api

import com.example.gooru.feature.data.body.BodyNewTicket
import com.example.gooru.feature.data.dto.support.ResponseSupportDto
import com.example.gooru.feature.data.dto.support.SupportTicketDto
import com.example.gooru.feature.data.dto.support.chat.ResponseSupportChatDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SupportApi {
    @GET(USER_SUPPORT)
    suspend fun getUserSupports(): ResponseSupportDto

    @GET(USER_SUPPORT_TICKED)
    suspend fun getAllMessage(
        @Query("ticket__id") ticketId: Int,
        @Query("ordering") ordering: String = "id"
    ): ResponseSupportChatDto

    @POST(USER_SUPPORT)
    suspend fun createNewTicket(@Body body: BodyNewTicket): SupportTicketDto

    private companion object {
        const val USER_SUPPORT = "api/v2/users/support/"
        const val USER_SUPPORT_TICKED = "api/v2/supportchat/"
    }

}