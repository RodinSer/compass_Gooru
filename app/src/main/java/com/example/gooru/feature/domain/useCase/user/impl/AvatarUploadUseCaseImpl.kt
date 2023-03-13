package com.example.gooru.feature.domain.useCase.user.impl

import android.net.Uri
import com.example.gooru.feature.domain.repository.UserRepository
import com.example.gooru.feature.domain.useCase.user.AvatarUploadUseCase

class AvatarUploadUseCaseImpl(
    private val userRepository: UserRepository
):AvatarUploadUseCase {
    override suspend fun imageUpload(uri: Uri?, userId: Int?) =
        userRepository.loadImage(uri,userId)


}