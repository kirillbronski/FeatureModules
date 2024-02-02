package com.kbcoding.profile.presentation.edit

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kbcoding.presentation.live.observeEvent
import com.kbcoding.presentation.viewBinding
import com.kbcoding.presentation.views.observe
import com.kbcoding.profile.R
import com.kbcoding.profile.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val viewModel by viewModels<EditProfileViewModel>()

    private val binding by viewBinding<FragmentEditProfileBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            observeEvents(savedInstanceState)
            setupListeners()
            observeState()
        }
    }

    private fun FragmentEditProfileBinding.observeEvents(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.initialUsernameLiveEvent.observeEvent(viewLifecycleOwner) {
                usernameEditText.setText(it)
            }
        }
    }

    private fun FragmentEditProfileBinding.setupListeners() {
        saveButton.setOnClickListener {
            viewModel.saveUsername(usernameEditText.text.toString())
        }
        cancelButton.setOnClickListener {
            viewModel.goBack()
        }
        root.setTryAgainListener { viewModel.load() }
    }

    private fun FragmentEditProfileBinding.observeState() {
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue) { state ->
            saveButton.isEnabled = state.enableSaveButton
            progressBar.isInvisible = !state.showProgress
        }
    }

}