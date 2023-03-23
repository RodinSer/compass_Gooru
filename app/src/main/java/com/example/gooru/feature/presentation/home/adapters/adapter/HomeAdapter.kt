package com.example.gooru.feature.presentation.home.adapters.adapter

import com.example.gooru.feature.domain.model.homepage.HomeInfo
import com.example.gooru.core.states.HomeButton
import com.example.gooru.feature.presentation.home.adapters.delegate.homeHorizontalDelegate
import com.example.gooru.feature.presentation.home.adapters.delegate.homeVerticalDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class HomeAdapter(
    onClickListener: (id: HomeButton) -> Unit
) : ListDelegationAdapter<List<HomeInfo>>() {

    init {
        delegatesManager.addDelegate(homeVerticalDelegate(onClickListener))
            .addDelegate(homeHorizontalDelegate(onClickListener))
    }
}