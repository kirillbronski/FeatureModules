package com.kbcoding.navigation.domain.repositories

import com.kbcoding.common.Container
import kotlinx.coroutines.flow.Flow

interface GetCartItemCountRepository {

    /**
     * Listen for the count of items added to the cart.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getCartItemCount(): Flow<Container<Int>>

}