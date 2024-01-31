package com.kbcoding.featuremodules.glue.catalog.repositories

import com.kbcoding.catalog.domain.repositories.CartRepository
import com.kbcoding.common.Container
import com.kbcoding.data.CartDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdapterCartRepository @Inject constructor(
    private val cartDataRepository: CartDataRepository,
) : CartRepository {

    override fun getProductIdentifiersInCart(): Flow<Container<Set<Long>>> {
        return cartDataRepository.getCart().map { container ->
            container.map { list ->
                list.map { it.productId }.toSet()
            }
        }
    }

    override fun reload() {
        cartDataRepository.reload()
    }

    override suspend fun addToCart(productId: Long) {
        cartDataRepository.addToCart(productId, quantity = 1)
    }

}