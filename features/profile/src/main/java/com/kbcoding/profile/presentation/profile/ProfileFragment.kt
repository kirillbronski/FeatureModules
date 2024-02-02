package com.kbcoding.profile.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kbcoding.presentation.viewBinding
import com.kbcoding.presentation.views.observe
import com.kbcoding.profile.R
import com.kbcoding.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModels<ProfileViewModel>()

    private val binding by viewBinding<FragmentProfileBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setupListeners()
            observeProfile()
        }
    }

    private fun FragmentProfileBinding.setupListeners() {
        editProfileButton.setOnClickListener { viewModel.editUsername() }
        logoutButton.setOnClickListener { viewModel.logout() }
        root.setTryAgainListener { viewModel.reload() }
    }

    private fun FragmentProfileBinding.observeProfile() {
        root.observe(viewLifecycleOwner, viewModel.profileLiveValue) { profile ->
            binding.emailTextView.text = profile.email
            binding.usernameTextView.text = profile.username
            binding.createdAtTextView.text = profile.createdAtString
        }
    }

}