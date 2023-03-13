package com.example.gooru.feature.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.constant.FORM_DATA
import com.example.gooru.core.extensions.createChangePasswordDialog
import com.example.gooru.core.extensions.createContactEditDialog
import com.example.gooru.core.extensions.createPersonEditDialog
import com.example.gooru.core.extensions.loadImage
import com.example.gooru.databinding.FragmentProfileBinding
import com.example.gooru.feature.domain.model.homepage.user.User
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)

    private val viewModel by viewModel<ProfileViewModel>()

    private val getItem = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.avatar.loadImage(uri)
        binding.activateSubscriptionButton.setOnClickListener { viewModel.loadImage(uri) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.user) { user -> userObserver(user) }

        dataObserver(viewModel.avatar) { binding.avatar.loadImage(it) }

        dataObserver(viewModel.tariffVisibility) { binding.userTariff.isVisible = it }

        binding.avatar.setOnClickListener { getItem.launch(FORM_DATA) }

        binding.personEdit.setOnClickListener {
            createPersonEditDialog { newFirstName, newLastName ->
                viewModel.updateUser(newFirstName, newLastName)
            }
        }

        binding.contactEdit.setOnClickListener {
            createContactEditDialog { number -> viewModel.updateUser(number) }
        }

        binding.changePasswordButton.setOnClickListener {
            createChangePasswordDialog { newPassword, oldPassword ->
                viewModel.changePassword(newPassword, oldPassword)
            }
        }
    }

    private fun userObserver(user: User) = binding.apply {
        avatar.loadImage(user.avatar)
        email.text = user.email
        firstName.text = user.firstName
        lastName.text = user.lastName
        phone.text = user.phoneNumber
        tariffName.text = user.tariff?.tariff.toString()
        startDate.text = user.tariff?.created
        finishDate.text = user.tariff?.finishDate
    }

}