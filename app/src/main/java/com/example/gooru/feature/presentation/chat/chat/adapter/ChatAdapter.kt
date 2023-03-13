package com.example.gooru.feature.presentation.chat.chat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.gooru.R
import com.example.gooru.databinding.ItemChatReceiverBinding
import com.example.gooru.databinding.ItemChatSendBinding
import com.example.gooru.feature.data.dto.support.chat.ChatMessage
import com.example.gooru.feature.presentation.chat.chat.adapter.viewholder.BaseChatViewHolder
import com.example.gooru.feature.presentation.chat.chat.adapter.viewholder.ChatReceiverViewHolder
import com.example.gooru.feature.presentation.chat.chat.adapter.viewholder.ChatSendViewHolder

class ChatAdapter(private val userId: Int) :
    ListAdapter<ChatMessage, BaseChatViewHolder>(ChatDiff()) {

    class ChatDiff() : DiffUtil.ItemCallback<ChatMessage>() {
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage) =
            oldItem.id == newItem.id

    }

    override fun getItemViewType(position: Int): Int {
        Log.d("Kart","${getItem(position).sender.id} = $userId")
        return when (getItem(position).sender.id == userId) {
            true ->  R.layout.item_chat_send
            false ->  R.layout.item_chat_receiver
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseChatViewHolder {
        Log.e("Kart","viewType = $viewType")
        return when (viewType) {
            R.layout.item_chat_send -> ChatSendViewHolder(
                ItemChatSendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ChatReceiverViewHolder(
                ItemChatReceiverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

    }


    override fun onBindViewHolder(holder: BaseChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

