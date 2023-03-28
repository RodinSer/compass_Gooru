package com.example.gooru.di

import com.example.gooru.app.MainViewModel
import com.example.gooru.feature.presentation.TariffViewModel
import com.example.gooru.feature.presentation.authorization.auth.AuthViewModel
import com.example.gooru.feature.presentation.authorization.registration.RegistrationViewModel
import com.example.gooru.feature.presentation.chat.chat.ChatViewModel
import com.example.gooru.feature.presentation.chat.tikets.TicketsViewModel
import com.example.gooru.feature.presentation.home.HomeViewModel
import com.example.gooru.feature.presentation.profile.ProfileViewModel
import com.example.gooru.feature.presentation.parsource.addparsource.AddParSourceViewModel
import com.example.gooru.feature.presentation.parsers.parser.favorite.FavoriteParserViewModel
import com.example.gooru.feature.presentation.parsers.parser.parserp.ParserViewModel
import com.example.gooru.feature.presentation.parsource.my.MyParSourceViewModel
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
    viewModelOf(::MainViewModel)
    viewModelOf(::TariffViewModel)
    viewModel{ChatViewModel(get(named("authHeader")),get(),get(),get(),get(),get())}
}