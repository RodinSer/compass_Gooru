package com.example.gooru.feature.presentation.chat.tikets

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemTicketBinding
import com.example.gooru.feature.domain.model.support.SupportTicket

class TicketVewHolder(private val binding: ItemTicketBinding, onClick: (id: Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private var ticedId: Int? = null

    init {
        binding.root.setOnClickListener {
            ticedId?.let { id -> onClick(id) }

        }
    }

    fun bind(item: SupportTicket) {
        ticedId = item.id
        binding.title.text = item.name
        binding.message.text = item.message
    }

}