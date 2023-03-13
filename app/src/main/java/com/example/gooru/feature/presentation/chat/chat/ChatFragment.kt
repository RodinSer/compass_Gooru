package com.example.gooru.feature.presentation.chat.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentChatBinding
import com.example.gooru.feature.presentation.chat.chat.adapter.ChatAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<FragmentChatBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentChatBinding.inflate(inflater)

    private val viewModel by viewModel<ChatViewModel>()

    private val adapter by lazy { ChatAdapter(viewModel.getUserId()) }

    private val ticketId: Int = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startWebSocket(ticketId)

        binding.recyclerView.adapter = adapter

        dataObserver(viewModel.message) {
            adapter.submitList(it)

        }

        binding.sendButton.setOnClickListener {
            viewModel.sendMessage(binding.newMessage.text.toString())
            binding.newMessage.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.closeWebSocKet()
    }

}