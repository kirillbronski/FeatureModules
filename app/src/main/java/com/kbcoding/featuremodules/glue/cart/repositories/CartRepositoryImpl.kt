package com.kbcoding.featuremodules.glue.cart.repositories

import com.kbcoding.common.Container
import com.kbcoding.data.CartDataRepository
import com.kbcoding.featuremodules.glue.cart.mappers.CartItemMapper
import com.kbcoding.features.cart.domain.entities.CartItem
import com.kbcoding.features.cart.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDataRepository: CartDataRepository,
    private val cartItemMapper: CartItemMapper,
) : CartRepository {

    override suspend fun changeQuantity(cartItemId: Long, newQuantity: Int) {
        cartDataRepository.changeQuantity(cartItemId, newQuantity)
    }

    override suspend fun deleteCartItems(cartItemIds: List<Long>) {
        cartDataRepository.deleteCartItem(cartItemIds)
    }

    override suspend fun getCartItemById(id: Long): CartItem {
        return cartItemMapper.toCartItem(cartDataRepository.getCartItemById(id))
    }

    override fun getCartItems(): Flow<Container<List<CartItem>>> {
        return cartDataRepository.getCart().map { container ->
            container.suspendMap { list ->
                list.map { cartItemMapper.toCartItem(it) }
            }
        }
    }

    override fun reload() {
        cartDataRepository.reload()
    }
}