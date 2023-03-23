package com.example.gooru.feature.presentation.parsers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemKeyWordBinding
import com.example.gooru.feature.presentation.parsers.adapters.holders.KeyWordViewHolder

class KeyWordAdapter : RecyclerView.Adapter<KeyWordViewHolder>() {

    private var items = mutableListOf<String>()

    fun addWord(word: String) {
        items.add(word)
        notifyItemInserted(items.lastIndex)
    }

    private fun removeWord(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItems() = items.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KeyWordViewHolder(
        ItemKeyWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) { position -> removeWord(position) }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: KeyWordViewHolder, position: Int) {
        holder.bind(items[position])
    }

}

