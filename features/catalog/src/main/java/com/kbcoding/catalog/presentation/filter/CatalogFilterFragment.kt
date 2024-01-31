package com.kbcoding.catalog.presentation.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.elveum.elementadapter.ElementListAdapter
import com.kbcoding.catalog.R
import com.kbcoding.catalog.databinding.FragmentFilterBinding
import com.kbcoding.catalog.domain.entities.ProductFilter
import com.kbcoding.presentation.args
import com.kbcoding.presentation.viewBinding
import com.kbcoding.presentation.viewModelCreator
import com.kbcoding.presentation.views.observe
import com.kbcoding.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint
import ua.cn.stu.multimodule.core.presentation.BaseScreen
import javax.inject.Inject

@AndroidEntryPoint
class CatalogFilterFragment : Fragment(R.layout.fragment_filter) {

    class Screen(
        val filter: ProductFilter
    ) : BaseScreen

    private val binding by viewBinding<FragmentFilterBinding>()

    @Inject lateinit var factory: CatalogFilterViewModel.Factory
    private val viewModel by viewModelCreator { factory.create(args()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = createFilterAdapter(
            onApply = {
                viewModel.applyFilter()
            }
        )
        with(binding) {
            filterRecyclerView.setupSimpleList()
            filterRecyclerView.adapter = adapter
            observeState(adapter)
            setupListeners()
        }
        viewModel.initBackListener(viewLifecycleOwner.lifecycleScope)
    }

    private fun FragmentFilterBinding.observeState(adapter: ElementListAdapter<FilterItem>) {
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue) { filterItems ->
            adapter.submitList(filterItems)
        }
    }

    private fun FragmentFilterBinding.setupListeners() {
        root.setTryAgainListener { viewModel.load() }
    }

}