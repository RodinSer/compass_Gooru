package com.example.gooru.feature.presentation.authorization.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.databinding.FragmentEmailVerificationBinding

class EmailVerificationFragment : BaseFragment<FragmentEmailVerificationBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentEmailVerificationBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}