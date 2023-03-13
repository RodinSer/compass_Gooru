package com.example.gooru.feature.presentation.parser.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemParsingBinding
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome

class ParSoursViewHolder(private val binding: ItemParsingBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ParSourceHome){
            binding.name.text = item.name
            binding.progressBarr.progress = 1
            binding.counter.text = item.lastTimeSync
        }
}