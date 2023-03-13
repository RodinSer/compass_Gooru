package com.example.gooru.feature.presentation.home.adapters.delegate

import com.example.gooru.databinding.ItemNestedHorizontalBinding
import com.example.gooru.databinding.ItemNestedVerticalBinding
import com.example.gooru.feature.data.home.ListHorizontal
import com.example.gooru.feature.data.home.ListVertical
import com.example.gooru.feature.domain.model.homepage.HomeInfo
import com.example.gooru.feature.presentation.home.HomeButton
import com.example.gooru.feature.presentation.home.adapters.adapter.NestedAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun homeHorizontalDelegate(
    onClickListener:(id:HomeButton)->Unit,
) = adapterDelegateViewBinding<ListHorizontal, HomeInfo, ItemNestedHorizontalBinding>({ layoutInflater, root ->
    ItemNestedHorizontalBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            binding.recyclerView.adapter = NestedAdapter(item.list){onClickListener(it)}
            binding.categoryName.text = item.nameCategory
        }
    }

fun homeVerticalDelegate(
    onClickListener:(id:HomeButton)->Unit,
) = adapterDelegateViewBinding<ListVertical, HomeInfo, ItemNestedVerticalBinding>({ layoutInflater, root ->
    ItemNestedVerticalBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            binding.recyclerView.adapter = NestedAdapter(item.list){onClickListener(it)}
            binding.categoryName.text = item.nameCategory
        }
    }