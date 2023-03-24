package com.example.gooru.di

import com.example.gooru.feature.domain.useCase.auth.AuthUseCase
import com.example.gooru.feature.domain.useCase.auth.ChangePasswordUseCase
import com.example.gooru.feature.domain.useCase.parsource.HomeParSourceUseCase
import com.example.gooru.feature.domain.useCase.auth.RegistrationUseCase
import com.example.gooru.feature.domain.useCase.auth.ResetPasswordUseCase
import com.example.gooru.feature.domain.useCase.user.UserInfoUseCase
import com.example.gooru.feature.domain.useCase.auth.impl.AuthUseCaseImpl
import com.example.gooru.feature.domain.useCase.auth.impl.ChangePasswordUseCaseImpl
import com.example.gooru.feature.domain.useCase.parsource.impl.HomeParSourceUseCaseImpl
import com.example.gooru.feature.domain.useCase.auth.impl.RegistrationUseCaseImpl
import com.example.gooru.feature.domain.useCase.auth.impl.ResetPasswordUseCaseImpl
import com.example.gooru.feature.domain.useCase.parser.*
import com.example.gooru.feature.domain.useCase.parser.impl.*
import com.example.gooru.feature.domain.useCase.parsource.NewParSourceUseCase
import com.example.gooru.feature.domain.useCase.parsource.impl.NewParSourceUseCaseImpl
import com.example.gooru.feature.domain.useCase.support.SupportAllTicketsUseCase
import com.example.gooru.feature.domain.useCase.support.SupportByTicketUseCase
import com.example.gooru.feature.domain.useCase.support.SupportNewTicketUseCase
import com.example.gooru.feature.domain.useCase.support.impl.SupportAllUseCaseImpl
import com.example.gooru.feature.domain.useCase.support.impl.SupportByTicketUseCaseImpl
import com.example.gooru.feature.domain.useCase.support.impl.SupportNewTicketUseCaseImpl
import com.example.gooru.feature.domain.useCase.tariff.AllTariffUseCase
import com.example.gooru.feature.domain.useCase.tariff.PayUseCase
import com.example.gooru.feature.domain.useCase.tariff.UserTariffUseCase
import com.example.gooru.feature.domain.useCase.tariff.impl.AllTariffUseCaseImpl
import com.example.gooru.feature.domain.useCase.tariff.impl.PayUseCaseImpl
import com.example.gooru.feature.domain.useCase.tariff.impl.UserTariffUseCaseImpl
import com.example.gooru.feature.domain.useCase.user.AvatarUploadUseCase
import com.example.gooru.feature.domain.useCase.user.UserChangePasswordUseCase
import com.example.gooru.feature.domain.useCase.user.UserUpdateUseCase
import com.example.gooru.feature.domain.useCase.user.impl.AvatarUploadUseCaseImpl
import com.example.gooru.feature.domain.useCase.user.impl.UserChangePasswordUseCaseImpl
import com.example.gooru.feature.domain.useCase.user.impl.UserInfoUseCaseImpl
import com.example.gooru.feature.domain.useCase.user.impl.UserUpdateUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {

    singleOf(::HomeParSourceUseCaseImpl){bind<HomeParSourceUseCase>()}
    singleOf(::CommentUseCaseImpl){bind<CommentUseCase>()}
}

val supportUseCaseModule= module {

    singleOf(::NewParSourceUseCaseImpl){bind<NewParSourceUseCase>()}
    singleOf(::SupportAllUseCaseImpl){bind<SupportAllTicketsUseCase>()}
    singleOf(::SupportByTicketUseCaseImpl){bind<SupportByTicketUseCase>()}
    singleOf(::SupportNewTicketUseCaseImpl){bind<SupportNewTicketUseCase>()}
}

val tariffUseCaseModule = module {

    singleOf(::UserTariffUseCaseImpl){bind<UserTariffUseCase>()}
    singleOf(::AllTariffUseCaseImpl){bind<AllTariffUseCase>()}
    singleOf(::PayUseCaseImpl){bind<PayUseCase>()}
}

val parserUseCaseModule = module {

    singleOf(::GetParserUseCaseImpl){bind<GetParserUseCase>()}
    singleOf(::FavoriteUseCaseImpl){bind<FavoriteUseCase>()}
    singleOf(::DownLoadURLUseCaseImpl){bind<DownLoadURLUseCase>()}
    singleOf(::EditParserUseCasaImpl){bind<EditParserUseCasa>()}
}

val userUseCaseModule = module {

    singleOf(::UserInfoUseCaseImpl){bind<UserInfoUseCase>()}
    singleOf(::UserUpdateUseCaseImpl){bind<UserUpdateUseCase>()}
    singleOf(::UserChangePasswordUseCaseImpl){bind<UserChangePasswordUseCase>()}
    singleOf(::AvatarUploadUseCaseImpl){bind<AvatarUploadUseCase>()}
}

val authUseCaseModule = module {

    singleOf(::RegistrationUseCaseImpl){bind<RegistrationUseCase>()}
    singleOf(::ResetPasswordUseCaseImpl){bind<ResetPasswordUseCase>()}
    singleOf(::AuthUseCaseImpl){bind<AuthUseCase>()}
    singleOf(::ChangePasswordUseCaseImpl){bind<ChangePasswordUseCase>()}
}