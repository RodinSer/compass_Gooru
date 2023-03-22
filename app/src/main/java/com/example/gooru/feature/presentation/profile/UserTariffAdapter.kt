package com.example.gooru.feature.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.databinding.ItemNestedTariffBinding

class UserTariffAdapter(private val list: List<String>?) :
    RecyclerView.Adapter<UserTariffAdapter.UserTariffViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= UserTariffViewHolder(
        ItemNestedTariffBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount()= list?.size?:0

    override fun onBindViewHolder(holder: UserTariffViewHolder, position: Int) {
        holder.bind(list?.get(position) ?: "")
    }

    class UserTariffViewHolder(private val binding: ItemNestedTariffBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.description.text = item
        }
    }
}