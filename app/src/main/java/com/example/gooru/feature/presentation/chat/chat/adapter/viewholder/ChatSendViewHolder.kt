package com.example.gooru.feature.presentation.chat.chat.adapter.viewholder

import com.example.gooru.core.extensions.loadCircleImage
import com.example.gooru.core.extensions.loadImage
import com.example.gooru.databinding.ItemChatSendBinding
import com.example.gooru.feature.domain.model.ChatMessage

class ChatSendViewHolder(private val binding: ItemChatSendBinding) :
    BaseChatViewHolder(binding) {

    override fun bind(item: ChatMessage) {
        binding.avatar.loadCircleImage(item.sender.avatar)
        binding.message.text = item.text
        binding.data.text = item.created
    }
}