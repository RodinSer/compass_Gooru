package com.example.gooru.di

import com.example.gooru.R
import com.example.gooru.feature.data.repositoryImpl.*
import com.example.gooru.feature.domain.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    single<ParSourceRepository> {
        ParSourceRepositoryImpl(
            get(),
            androidContext().resources.getStringArray(R.array.popular_parsing).toList(),
        )
    }

    singleOf(::ParserRepositoryImpl){bind<ParserRepository>()}

    singleOf(::UserRepositoryImpl){bind<UserRepository>()}

    singleOf(::TariffRepositoryImpl){bind<TariffRepository>()}

    singleOf(::PagingParserImpl){bind<PagingParser>()}

    singleOf(::SupportRepositoryImpl){bind<SupportRepository>()}

    singleOf(::CommentRepositoryImpl){bind<CommentRepository>()}

    singleOf(::PagingSupportTicket)


}