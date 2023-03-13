package com.example.gooru.feature.data.dto.support

class ResponseSupportDto(
    val count: Int,
    val next: Any,
    val previous: Any,
   private val results: List<SupportTicketDto>
){
    fun toListSupportTicket() = results.map { it.toSupport() }
}