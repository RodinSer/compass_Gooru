package com.example.gooru.di

import com.example.gooru.feature.presentation.authorization.auth.AuthViewModel
import com.example.gooru.feature.presentation.authorization.registration.RegistrationViewModel
import com.example.gooru.feature.presentation.chat.chat.ChatViewModel
import com.example.gooru.feature.presentation.chat.tikets.TicketsViewModel
import com.example.gooru.feature.presentation.home.HomeViewModel
import com.example.gooru.feature.presentation.parser.addparsource.AddParSourceViewModel
import com.example.gooru.feature.presentation.parser.parser.ParserViewModel
import com.example.gooru.feature.presentation.parser.favorite.FavoriteParserViewModel
import com.example.gooru.feature.presentation.parser.parsource.MyParSourceViewModel
import com.example.gooru.feature.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModel = module {

    viewModel { AuthViewModel(get(), get(), get(), get()) }

    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get()) }

    viewModel { RegistrationViewModel(get(), get()) }

    viewModel { ProfileViewModel(get(), get(), get(), get(), get(), get()) }

    viewModel { ParserViewModel(get(), get(), get(),get(),get(),get()) }

    viewModel { (FavoriteParserViewModel(get(),get())) }

    viewModel { MyParSourceViewModel(get(),get()) }

    viewModel { (TicketsViewModel(get(),get(),get())) }

    viewModel { ChatViewModel(get(named("authHeader")), get(), get(), get(),get()) }

    viewModel { AddParSourceViewModel(get(), get()) }

}