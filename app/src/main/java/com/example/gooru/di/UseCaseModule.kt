package com.example.gooru.di

import com.example.gooru.feature.domain.useCase.auth.AuthUseCase
import com.example.gooru.feature.domain.useCase.auth.ChangePasswordUseCase
import com.example.gooru.feature.domain.useCase.parsource.ParSourceUseCase
import com.example.gooru.feature.domain.useCase.auth.RegistrationUseCase
import com.example.gooru.feature.domain.useCase.auth.ResetPasswordUseCase
import com.example.gooru.feature.domain.useCase.user.UserInfoUseCase
import com.example.gooru.feature.domain.useCase.auth.impl.AuthUseCaseImpl
import com.example.gooru.feature.domain.useCase.auth.impl.ChangePasswordUseCaseImpl
import com.example.gooru.feature.domain.useCase.parsource.impl.ParSourceUseCaseImpl
import com.example.gooru.feature.domain.useCase.auth.impl.RegistrationUseCaseImpl
import com.example.gooru.feature.domain.useCase.auth.impl.ResetPasswordUseCaseImpl
import com.example.gooru.feature.domain.useCase.parser.DownLoadURLUseCase
import com.example.gooru.feature.domain.useCase.parser.EditParserUseCasa
import com.example.gooru.feature.domain.useCase.parser.FavoriteUseCase
import com.example.gooru.feature.domain.useCase.parser.impl.FavoriteUseCaseImpl
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import com.example.gooru.feature.domain.useCase.parser.impl.DownLoadURLUseCaseImpl
import com.example.gooru.feature.domain.useCase.parser.impl.EditParserUseCasaImpl
import com.example.gooru.feature.domain.useCase.parser.impl.GetParserUseCaseImpl
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
import org.koin.dsl.module

val useCaseModule = module {
    single<ParSourceUseCase> { ParSourceUseCaseImpl(get()) }

    single<NewParSourceUseCase> { NewParSourceUseCaseImpl(get()) }

    single<SupportAllTicketsUseCase> { SupportAllUseCaseImpl(repository = get()) }

    single<SupportByTicketUseCase> { SupportByTicketUseCaseImpl(get()) }

    single<SupportNewTicketUseCase> { SupportNewTicketUseCaseImpl(get()) }
}

val tariffUseCaseModule = module {
    single<UserTariffUseCase> { UserTariffUseCaseImpl(get()) }

    single<AllTariffUseCase> { AllTariffUseCaseImpl(get()) }

    single<PayUseCase> { PayUseCaseImpl(get()) }
}

val parserUseCaseModule = module {
    single<GetParserUseCase> { GetParserUseCaseImpl(get()) }

    single<FavoriteUseCase> { FavoriteUseCaseImpl(get()) }

    single<DownLoadURLUseCase> { DownLoadURLUseCaseImpl(get()) }

    single<EditParserUseCasa> { EditParserUseCasaImpl(get()) }
}

val userUseCaseModule = module {
    single<UserInfoUseCase> { UserInfoUseCaseImpl(get()) }

    single<UserUpdateUseCase> { UserUpdateUseCaseImpl(get()) }

    single<UserChangePasswordUseCase> { UserChangePasswordUseCaseImpl(get()) }

    single<AvatarUploadUseCase> { AvatarUploadUseCaseImpl(get()) }
}

val authUseCaseModule = module {
    single<RegistrationUseCase> { RegistrationUseCaseImpl(get()) }

    single<ResetPasswordUseCase> { ResetPasswordUseCaseImpl(get()) }

    single<AuthUseCase> { AuthUseCaseImpl(get()) }

    single<ChangePasswordUseCase> { ChangePasswordUseCaseImpl(get()) }
}