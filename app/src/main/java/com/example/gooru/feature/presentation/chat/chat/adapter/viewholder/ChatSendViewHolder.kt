package com.example.gooru.feature.presentation.chat.chat.adapter.viewholder

import com.example.gooru.core.extensions.loadImage
import com.example.gooru.databinding.ItemChatSendBinding
import com.example.gooru.feature.data.dto.support.chat.ChatMessage

class ChatSendViewHolder(private val binding: ItemChatSendBinding) :
    BaseChatViewHolder(binding) {

    override fun bind(item: ChatMessage) {
        binding.avatar.loadImage(item.sender.avatar)
        binding.message.text = item.text
        binding.name.text = item.sender.first_name
        binding.data.text = item.created_at
    }
}