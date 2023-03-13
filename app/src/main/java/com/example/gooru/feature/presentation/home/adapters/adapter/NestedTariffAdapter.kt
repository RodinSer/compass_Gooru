package com.example.gooru.feature.presentation.home.adapters.adapter

import com.example.gooru.feature.presentation.home.adapters.delegate.descriptionTariffDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class NestedTariffAdapter(list: List<String>) :
    ListDelegationAdapter<List<String>>() {

    init {
        delegatesManager.addDelegate(descriptionTariffDelegate())
        setItems(list)
    }
}