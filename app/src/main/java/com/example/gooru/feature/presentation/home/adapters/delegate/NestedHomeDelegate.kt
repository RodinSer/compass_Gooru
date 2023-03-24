package com.example.gooru.feature.presentation.home.adapters.delegate

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import com.example.gooru.R
import com.example.gooru.core.extensions.loadCircleImage
import com.example.gooru.core.extensions.setText
import com.example.gooru.core.generation.FAQ
import com.example.gooru.databinding.ItemAddParsourceBinding
import com.example.gooru.databinding.ItemFaqBinding
import com.example.gooru.databinding.ItemMyParsingTasksBinding
import com.example.gooru.databinding.ItemNestedTariffBinding
import com.example.gooru.databinding.ItemParsingPopularBinding
import com.example.gooru.databinding.ItemTariffBinding
import com.example.gooru.databinding.ItemUserBinding
import com.example.gooru.feature.domain.model.homepage.NewParSource
import com.example.gooru.feature.domain.model.homepage.HomePage
import com.example.gooru.feature.domain.model.homepage.parsource.ParSourceHome
import com.example.gooru.feature.domain.model.homepage.parsource.PopularParSource
import com.example.gooru.feature.domain.model.homepage.tariff.Tariff
import com.example.gooru.feature.domain.model.homepage.user.User
import com.example.gooru.core.states.HomeButton
import com.example.gooru.databinding.ItemAllParsourceBinding
import com.example.gooru.feature.domain.model.homepage.NextButton
import com.example.gooru.feature.presentation.home.adapters.adapter.NestedTariffAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun userDelegate() =
    adapterDelegateViewBinding<User, HomePage, ItemUserBinding>({ layoutInflater, root ->
        ItemUserBinding.inflate(
            layoutInflater,
            root,
            false
        )
    }) {
        bind {
            binding.avatar.loadCircleImage(item.avatar)
            binding.firstName.text = item.firstName
            binding.lastName.text = item.lastName
        }
    }

fun parSourceDelegate(
    onClickListener: (button: HomeButton) -> Unit
) = adapterDelegateViewBinding<ParSourceHome, HomePage, ItemMyParsingTasksBinding>({ layoutInflater, root ->
        ItemMyParsingTasksBinding.inflate(
            layoutInflater,
            root,
            false
        )
    }) {

        binding.root.setOnClickListener {
            onClickListener(HomeButton.PARSER.apply { id = item.id})
        }

        bind {
            binding.name.text = item.name
            binding.progressBarr.progress = 1
            binding.counter.text = item.lastTimeSync
        }
    }

@SuppressLint("SetTextI18n")
fun tariffDelegate(onClickListener: (button: HomeButton) -> Unit) =
    adapterDelegateViewBinding<Tariff, HomePage, ItemTariffBinding>({ layoutInflater, root ->
        ItemTariffBinding.inflate(layoutInflater, root, false)
    }) {

        binding.buyButton.setOnClickListener {
            onClickListener(HomeButton.TARIFF.apply { id = item.id })
        }

        bind {
            binding.price.setText(R.string.rub, item.cost.toString())
            binding.recyclerView.adapter = NestedTariffAdapter(item.description)
            binding.name.text = item.name
        }
    }

fun faqDelegate() =
    adapterDelegateViewBinding<FAQ, HomePage, ItemFaqBinding>({ layoutInflater, root ->
        ItemFaqBinding.inflate(layoutInflater, root, false)
    }) {

        binding.root.setOnClickListener {
            binding.response.isVisible = !binding.response.isVisible
        }

        bind {
            binding.title.text = item.question
            binding.response.text = item.response
        }
    }

fun popularDelegate() =
    adapterDelegateViewBinding<PopularParSource, HomePage, ItemParsingPopularBinding>({ layoutInflater, root ->
        ItemParsingPopularBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            binding.test.text = item.popular
        }
    }

fun descriptionTariffDelegate() =
    adapterDelegateViewBinding<String, String, ItemNestedTariffBinding>({ layoutInflater, root ->
        ItemNestedTariffBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            binding.description.text = item
        }
    }

fun parSourceAddDelegate(onClickListener: (button: HomeButton) -> Unit) =
    adapterDelegateViewBinding<NewParSource, HomePage, ItemAddParsourceBinding>({ layoutInflater, root ->
        ItemAddParsourceBinding.inflate(layoutInflater, root, false)
    }) {
        binding.root.setOnClickListener {
            onClickListener(HomeButton.NEW_SOURCE)
        }
    }

fun parSourceAllDelegate(onClickListener: (button: HomeButton) -> Unit) =
    adapterDelegateViewBinding<NextButton, HomePage, ItemAllParsourceBinding>({ layoutInflater, root ->
        ItemAllParsourceBinding.inflate(layoutInflater, root, false)
    }) {
        binding.root.setOnClickListener {
            onClickListener(HomeButton.ALL)
        }
    }