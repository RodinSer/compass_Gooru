package com.example.gooru.feature.presentation.authorization.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.gooru.R
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.createResetPasswordDialog
import com.example.gooru.core.extensions.showError
import com.example.gooru.core.states.AuthState
import com.example.gooru.databinding.FragmentAuthBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    private val viewModel by viewModel<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObserver(viewModel.state) { state -> observer(state) }

        enableButtonListener()

        clickAuthButton()

        clickResetPasswordButton()

    }

    private fun enableButtonListener() {
        binding.email.doOnTextChanged { email, _, _, _ ->
            viewModel.emailValidation(email.toString())
        }
        binding.password.doOnTextChanged { password, _, _, _ ->
            viewModel.passwordValidation(password.toString())
        }
    }

    private fun clickResetPasswordButton() = binding.resetPasswordButton.setOnClickListener {
        createResetPasswordDialog(binding.email.text.toString()) { email ->
            viewModel.resetPassword(email)
        }
    }

    private fun clickAuthButton() =
        binding.nextButton.setOnClickListener {
            startAuth()
        }

    private fun observer(state: AuthState) {
        binding.progressBarr.isVisible = state == AuthState.LOADING
        binding.nextButton.isVisible = state != AuthState.LOADING
        binding.errorMessage.isVisible = state == AuthState.ERROR_400
        binding.nextButton.isEnabled = state == AuthState.ENABLED_BUTTON
        if (state == AuthState.SUCCESS_AUTH)
            findNavController().navigate(R.id.action_startAuthFragment_to_homeFragment)
        if (state == AuthState.ERROR_WI_FI) showError { startAuth() }
    }

    private fun startAuth() {
        viewModel.getToken(binding.email.text.toString(), binding.password.text.toString())
    }

}