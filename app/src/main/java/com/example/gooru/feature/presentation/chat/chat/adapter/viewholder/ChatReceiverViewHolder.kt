package com.example.gooru.feature.presentation.chat.chat.adapter.viewholder

import com.example.gooru.core.extensions.loadCircleImage
import com.example.gooru.core.extensions.loadImage
import com.example.gooru.databinding.ItemChatReceiverBinding
import com.example.gooru.feature.domain.model.ChatMessage

class ChatReceiverViewHolder(private val binding: ItemChatReceiverBinding) :
    BaseChatViewHolder(binding) {
    override fun bind(item: ChatMessage) {
        binding.avatar.loadCircleImage(item.sender.avatar)
        binding.message.text = item.text
        binding.data.text = item.created
    }
}