package com.example.gooru.feature.presentation.home.adapters.adapter

import com.example.gooru.feature.domain.model.homepage.HomePage
import com.example.gooru.core.states.HomeButton
import com.example.gooru.feature.presentation.home.adapters.delegate.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NestedAdapter(
    list: List<HomePage>, onClickListener: (id: HomeButton) -> Unit
) : ListDelegationAdapter<List<HomePage>>() {

    init {
        delegatesManager.addDelegate(parSourceDelegate(onClickListener))
            .addDelegate(tariffDelegate(onClickListener)).addDelegate(popularDelegate())
            .addDelegate(parSourceAddDelegate(onClickListener)).addDelegate(userDelegate())
            .addDelegate(parSourceAllDelegate(onClickListener)).addDelegate(userDelegate())
            .addDelegate(faqDelegate())

        setItems(list)
    }

}