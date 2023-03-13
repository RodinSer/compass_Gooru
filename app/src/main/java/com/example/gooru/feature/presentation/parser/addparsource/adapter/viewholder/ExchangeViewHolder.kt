package com.example.gooru.feature.presentation.parser.addparsource.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemExchangeParsingBinding
import com.example.gooru.feature.presentation.parser.addparsource.ExchangeParsing

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