package com.example.gooru.feature.presentation.chat.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentChatBinding
import com.example.gooru.feature.domain.model.ChatMessage
import com.example.gooru.feature.presentation.chat.chat.adapter.ChatAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<FragmentChatBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentChatBinding.inflate(inflater)

    private val viewModel by viewModel<ChatViewModel>()

    private val adapter by lazy { ChatAdapter(viewModel.getUserId()) }

    private val args by navArgs<ChatFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startFragment()
        binding.recyclerView.adapter = adapter

        dataObserver(viewModel.message) { messages -> setAdapter(messages) }

        binding.sendButton.setOnClickListener { sendMessage() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.closeWebSocKet()
    }

    private fun startFragment() {
        viewModel.getMessage(args.ticketId)
        viewModel.startWebSocket(args.ticketId)
    }

    private fun setAdapter(messages: List<ChatMessage>) {
        adapter.submitList(messages)
        binding.recyclerView.smoothScrollToPosition(messages.size)
    }

    private fun sendMessage() {
        viewModel.sendMessage(binding.newMessage.text.toString())
        binding.newMessage.setText("")
    }
}