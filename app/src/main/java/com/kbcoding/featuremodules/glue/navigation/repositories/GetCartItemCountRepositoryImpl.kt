package com.kbcoding.featuremodules.glue.navigation.repositories

import com.kbcoding.common.Container
import com.kbcoding.data.CartDataRepository
import com.kbcoding.navigation.domain.repositories.GetCartItemCountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartItemCountRepositoryImpl @Inject constructor(
    private val cartDataRepository: CartDataRepository,
) : GetCartItemCountRepository {

    override fun getCartItemCount(): Flow<Container<Int>> {
        return cartDataRepository.getCart().map { container ->
            container.map { list -> list.size }
        }
    }

}