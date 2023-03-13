package com.example.gooru.feature.presentation.parser.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemParserBinding
import com.example.gooru.feature.data.dto.parser.FavoriteParserDto
import com.example.gooru.feature.presentation.parser.adapters.holders.ParserViewHolder

class FavoriteListAdapter : RecyclerView.Adapter<ParserViewHolder>() {

    private var items= listOf<FavoriteParserDto>()

    fun setItems(list:List<FavoriteParserDto>){
        items = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ParserViewHolder(
        ItemParserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ){}

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ParserViewHolder, position: Int) {
        holder.bind(items[position].parser.toParser())
    }
}
