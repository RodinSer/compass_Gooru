package com.example.gooru.feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.startNewApp
import com.example.gooru.databinding.FragmentTariffBinding
import com.example.gooru.feature.presentation.home.adapters.adapter.NestedAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TariffFragment : BaseFragment<FragmentTariffBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentTariffBinding.inflate(inflater)

    private val viewModel by viewModel<TariffViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.tariff) { list ->
            binding.recyclerView.adapter = NestedAdapter(list) { button->redirect(button.id) }
        }

    }

    private fun redirect(id: Int) {
        viewModel.getPayUrl(id) { url -> startNewApp(url) }
    }

}