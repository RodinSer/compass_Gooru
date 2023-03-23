package com.example.gooru.feature.presentation.tabparsource.parser.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemParsingBinding
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome

class ParSoursViewHolder(
    private val binding: ItemParsingBinding,
    onClick: (parSourceId: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    private var parSourceId: Int? = null

    init {
        binding.root.setOnClickListener {
            parSourceId?.let { id -> onClick(id) }
        }
    }

    fun bind(item: ParSourceHome) {
        parSourceId = item.id
        binding.name.text = item.name
        binding.progressBarr.progress = 1
        binding.counter.text = item.lastTimeSync
    }
}