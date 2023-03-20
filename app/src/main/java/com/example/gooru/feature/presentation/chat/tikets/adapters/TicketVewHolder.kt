package com.example.gooru.feature.presentation.chat.tikets.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemTicketBinding
import com.example.gooru.feature.domain.model.support.SupportTicket

class TicketVewHolder(private val binding: ItemTicketBinding, onClick: (id: Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private var ticketId: Int? = null

    init {
        binding.root.setOnClickListener {
            ticketId?.let { id -> onClick(id) }
        }
    }

    fun bind(item: SupportTicket) {
        ticketId = item.id
        binding.title.text = item.topicName
        binding.message.text = item.message
        binding.status.text = item.status
        binding.date.text = item.date
    }

}