package com.example.gooru.di

import com.example.gooru.feature.presentation.authorization.auth.AuthViewModel
import com.example.gooru.feature.presentation.authorization.registration.RegistrationViewModel
import com.example.gooru.feature.presentation.chat.chat.ChatViewModel
import com.example.gooru.feature.presentation.chat.tikets.TicketsViewModel
import com.example.gooru.feature.presentation.home.HomeViewModel
import com.example.gooru.feature.presentation.parser.addparsource.AddParSourceViewModel
import com.example.gooru.feature.presentation.parser.parser.parserp.ParserViewModel
import com.example.gooru.feature.presentation.parser.parser.favorite.FavoriteParserViewModel
import com.example.gooru.feature.presentation.parser.parsource.MyParSourceViewModel
import com.example.gooru.feature.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::RegistrationViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ParserViewModel)
    viewModelOf(::FavoriteParserViewModel)
    viewModelOf(::MyParSourceViewModel)
    viewModelOf(::TicketsViewModel)
    viewModelOf(::AddParSourceViewModel)
    viewModel{ChatViewModel(get(named("authHeader")),get(),get(),get(),get(),get())}
}