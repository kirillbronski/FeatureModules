package com.kbcoding.catalog.presentation.catalog

import com.kbcoding.catalog.domain.AddToCartUseCase
import com.kbcoding.catalog.domain.GetCatalogUseCase
import com.kbcoding.catalog.domain.entities.ProductFilter
import com.kbcoding.catalog.domain.entities.ProductWithCartInfo
import com.kbcoding.catalog.presentation.CatalogFilterRouter
import com.kbcoding.catalog.presentation.CatalogRouter
import com.kbcoding.common.Container
import com.kbcoding.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val catalogRouter: CatalogRouter,
    private val catalogFilterRouter: CatalogFilterRouter,
) : BaseViewModel() {

    private val filterFlow = MutableStateFlow(ProductFilter.EMPTY)

    val stateLiveValue = filterFlow
        .flatMapLatest { filter ->
            getCatalogUseCase.getProducts(filter).map { container ->
                container.map { products ->
                    State(products, filter)
                }
            }
        }
        .toLiveValue(initialValue = Container.Pending)

    init {
        viewModelScope.launch {
            catalogFilterRouter.receiveFilterResults().collectLatest {
                filterFlow.value = it
            }
        }
    }

    fun launchFilter() = debounce {
        catalogFilterRouter.launchFilter(filterFlow.value)
    }

    fun launchDetails(productWithCartInfo: ProductWithCartInfo) = debounce {
        catalogRouter.launchDetails(productWithCartInfo.product.id)
    }

    fun addToCart(productWithCartInfo: ProductWithCartInfo) = debounce {
        viewModelScope.launch {
            addToCartUseCase.addToCart(productWithCartInfo.product)

        }
    }

    class State(
        val products: List<ProductWithCartInfo>,
        val filter: ProductFilter,
    )

}