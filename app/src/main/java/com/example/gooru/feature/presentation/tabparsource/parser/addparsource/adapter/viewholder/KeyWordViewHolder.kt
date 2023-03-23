package com.example.gooru.feature.presentation.tabparsource.parser.addparsource.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemKeyWordBinding

class KeyWordViewHolder(
    private val binding: ItemKeyWordBinding,
    private val onClickListener: (position: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var itemPosition: Int? = null

    init {
        binding.root.setOnClickListener {
            itemPosition?.let { position -> onClickListener(position) }

        }
    }

    fun bind(item: String) {
        itemPosition = absoluteAdapterPosition
        binding.keyWord.text = item
    }
}