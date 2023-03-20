package com.example.gooru.di

import com.example.gooru.R
import com.example.gooru.feature.data.repositoryImpl.*
import com.example.gooru.feature.domain.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<ParSourceRepository> {
        ParSourceRepositoryImpl(
            get(),
            androidContext().resources.getStringArray(R.array.popular_parsing).toList(),
        )
    }

    single<ParserRepository> { ParserRepositoryImpl(get()) }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    single<TariffRepository> { TariffRepositoryImpl(get()) }

    single<PagingParser> { PagingParserImpl(get()) }

    single<PagingSupportTicket> { PagingSupportTicket(get()) }

    single<SupportRepository> { SupportRepositoryImpl(get()) }

    single<CommentRepository> { CommentRepositoryImpl(get()) }

}