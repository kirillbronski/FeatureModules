package com.kbcoding.features.cart.presentation.quantity

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kbcoding.features.cart.R
import com.kbcoding.features.cart.databinding.DialogQuantityBinding
import com.kbcoding.presentation.args
import com.kbcoding.presentation.live.observeEvent
import com.kbcoding.presentation.viewModelCreator
import com.kbcoding.presentation.views.observe
import dagger.hilt.android.AndroidEntryPoint
import ua.cn.stu.multimodule.core.presentation.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class EditQuantityDialogFragment : DialogFragment() {

    class Screen(
        val cartItemId: Long,
        val initialQuantity: Int,
    ) : BaseScreen

    @Inject
    lateinit var factory: EditQuantityViewModel.Factory
    private val viewModel by viewModelCreator { factory.create(args()) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setNegativeButton(R.string.cart_action_cancel, null)
            .setPositiveButton(R.string.cart_action_save, null)
            .setTitle(R.string.cart_edit_quantity)
            .setView(R.layout.dialog_quantity)
            .create()
        dialog.setOnShowListener {
            val view = dialog.findViewById<View>(R.id.dialogRoot)!!
            val binding = DialogQuantityBinding.bind(view)
            with(binding) {
                if (savedInstanceState == null) {
                    observeInitialQuantity(dialog)
                }
                observeState(dialog)
                setupListeners()
            }
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val quantityInput = binding.quantityEditText.text.toString()
                viewModel.saveNewQuantity(quantityInput)
            }
        }
        return dialog
    }

    private fun DialogQuantityBinding.observeState(lifecycleOwner: LifecycleOwner) {
        root.observe(lifecycleOwner, viewModel.stateLiveValue) { state ->
            hintTextView.text = getString(R.string.cart_max_quantity, state.maxQuantity)
        }
    }

    private fun DialogQuantityBinding.observeInitialQuantity(lifecycleOwner: LifecycleOwner) {
        viewModel.initialQuantityLiveEvent.observeEvent(lifecycleOwner) {
            quantityEditText.setText(it.toString())
        }
    }

    private fun DialogQuantityBinding.setupListeners() {
        root.setTryAgainListener { viewModel.load() }
    }

}