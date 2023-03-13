package com.example.gooru.feature.presentation.chat.tikets

import androidx.recyclerview.widget.DiffUtil
import com.example.gooru.feature.domain.model.support.SupportTicket

class SupportTicketDiff : DiffUtil.ItemCallback<SupportTicket>() {
    override fun areItemsTheSame(oldItem: SupportTicket, newItem: SupportTicket) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SupportTicket, newItem: SupportTicket) =
        oldItem == newItem

}