package com.example.gooru.feature.presentation.chat.tikets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.createNewTicketDialog
import com.example.gooru.databinding.FragmentTicketsBinding
import com.example.gooru.feature.presentation.chat.tikets.adapters.TicketsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : BaseFragment<FragmentTicketsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentTicketsBinding.inflate(inflater)

    private val viewModel by viewModel<TicketsViewModel>()

    // private val adapter by lazy { TicketsPagerAdapter{onTokedClick(it)} }
    private val adapter by lazy { TicketsListAdapter { onTokedClick(it) } }


    private fun onTokedClick(id: Int) {
        findNavController().navigate(
            TicketsFragmentDirections.actionTicketsFragmentToChatFragment(id)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newTicketButton.setOnClickListener {
            createNewTicketDialog { message, theme ->
                viewModel.creteNewTicket(theme, message){
                    binding.recyclerView.adapter?.notifyItemInserted(0)
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            }
        }

        dataObserver(viewModel.ticket) { list->
            adapter.submitList(list)
            binding.recyclerView.adapter = adapter
        }
    }

}