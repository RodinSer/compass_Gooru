package com.example.gooru.feature.data.dto.support

import com.example.gooru.feature.domain.model.support.SupportTicket

class SupportTicketDto(
  private  val email: String,
  private val id: Int,
  private  val message: String,
  private val name: String,
  private val parser: Int,
  private val phone_number: String,
  private val status: Int,
  private val topic_type: Int
){
    fun toSupport()=SupportTicket(email,id,message,name,parser,phone_number,status,topic_type)
}