package com.example.gooru.feature.presentation.parsers.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.core.generation.ExchangeParsing
import com.example.gooru.databinding.ItemExchangeParsingBinding

class ExchangeViewHolder(
    private val binding: ItemExchangeParsingBinding,
    private val onClickListener: (position: Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {


    init {
        binding.root.setOnClickListener {
            binding.text.isSelected = !binding.text.isSelected
            onClickListener(absoluteAdapterPosition)
        }
    }

    fun bind(item: ExchangeParsing) {
        binding.text.text = item.name
    }
}