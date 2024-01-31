package com.kbcoding.catalog.presentation.catalog

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.simpleAdapter
import com.kbcoding.catalog.R
import com.kbcoding.catalog.databinding.FragmentCatalogBinding
import com.kbcoding.catalog.databinding.ItemProductBinding
import com.kbcoding.catalog.domain.entities.ProductWithCartInfo
import com.kbcoding.presentation.loadResource
import com.kbcoding.presentation.loadUrl
import com.kbcoding.presentation.viewBinding
import com.kbcoding.presentation.views.observe
import com.kbcoding.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val binding by viewBinding<FragmentCatalogBinding>()

    private val viewModel by viewModels<CatalogViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = createAdapter()

        with(binding) {
            observeState(adapter)
            setupList(adapter)
            setupListeners()
        }
    }

    private fun FragmentCatalogBinding.observeState(adapter: SimpleBindingAdapter<ProductWithCartInfo>) {
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue) { state ->
            adapter.submitList(state.products)
        }
    }

    private fun FragmentCatalogBinding.setupList(adapter: SimpleBindingAdapter<ProductWithCartInfo>) {
        productsRecyclerView.setupSimpleList()
        productsRecyclerView.adapter = adapter
    }

    private fun FragmentCatalogBinding.setupListeners() {
        sortAndFilterButton.setOnClickListener {
            viewModel.launchFilter()
        }
    }

    private fun createAdapter() = simpleAdapter<ProductWithCartInfo, ItemProductBinding> {
        areItemsSame = { oldItem, newItem -> oldItem.product.id == newItem.product.id }
        areContentsSame = { oldItem, newItem -> oldItem == newItem }

        bind { productWithCartInfo ->
            val product = productWithCartInfo.product
            nameTextView.text = product.name
            shortDescriptionTextView.text = product.shortDetails
            originPriceTextView.text = product.price.text
            if (product.priceWithDiscount == null) {
                priceWithDiscountTextView.isVisible = false
                originPriceTextView.setBackgroundColor(Color.TRANSPARENT)
            } else {
                priceWithDiscountTextView.isVisible = true
                priceWithDiscountTextView.text = product.priceWithDiscount.text
                originPriceTextView.setBackgroundResource(com.kbcoding.theme.R.drawable.core_theme_diagonal_line)
            }
            addToCartImageView.isEnabled = !productWithCartInfo.isInCart
            addToCartImageView.setImageResource(
                if (productWithCartInfo.isInCart)
                    com.kbcoding.theme.R.drawable.core_theme_ic_done
                else
                    R.drawable.ic_add_to_cart
            )
            if (product.photo.isNotBlank()) {
                productImageView.loadUrl(product.photo)
            } else {
                productImageView.loadResource(com.kbcoding.theme.R.drawable.core_theme_placeholder)
            }
            categoryHintTextView.text = getString(R.string.catalog_category, product.category)
        }

        listeners {
            addToCartImageView.onClick { productWithCartInfo ->
                viewModel.addToCart(productWithCartInfo)
            }
            root.onClick { productWithCartInfo ->
                viewModel.launchDetails(productWithCartInfo)
            }
        }
    }

}