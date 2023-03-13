package com.example.gooru.feature.presentation.chat.tikets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.gooru.R
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TicketsFragment : BaseFragment<FragmentTicketsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentTicketsBinding.inflate(inflater)

    private val viewModel by viewModel<TicketsViewModel>()

    private val adapter by lazy { TicketsAdapter(){onTokedClick(it)} }

    fun onTokedClick(id:Int){
        findNavController().navigate(R.id.action_ticketsFragment_to_chatFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter



        dataObserver(viewModel.tickets){
            adapter.submitData(it)
        }
    }

}