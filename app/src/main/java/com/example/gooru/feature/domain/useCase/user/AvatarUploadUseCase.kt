package com.example.gooru.feature.domain.useCase.user

import android.net.Uri
import com.example.gooru.feature.domain.model.UserAvatar

interface AvatarUploadUseCase {

    suspend fun imageUpload(uri: Uri?, userId: Int?): UserAvatar

}