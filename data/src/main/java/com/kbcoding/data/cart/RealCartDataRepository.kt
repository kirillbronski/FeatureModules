package ua.cn.stu.multimodule.data.cart

import com.kbcoding.common.Container
import com.kbcoding.common.NotFoundException
import com.kbcoding.common.flow.LazyFlowSubjectFactory
import com.kbcoding.data.CartDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.kbcoding.data.cart.entities.CartItemDataEntity
import com.kbcoding.data.cart.sources.CartDataSource
import com.kbcoding.data.settings.SettingsDataSource
import javax.inject.Inject

class RealCartDataRepository @Inject constructor(
    private val cartDataSource: CartDataSource,
    private val settingsDataSource: SettingsDataSource,
    scope: CoroutineScope,
    lazyFlowSubjectFactory: LazyFlowSubjectFactory,
) : CartDataRepository {

    init {
        scope.launch {
            settingsDataSource.listenToken().collect {
                if (it == null) {
                    cartDataSource.clearCart()
                    cartSubject.updateWith(Container.Success(emptyList()))
                }
            }
        }
    }

    private val cartSubject = lazyFlowSubjectFactory.create {
        cartDataSource.getCart()
    }

    override fun getCart(): Flow<Container<List<CartItemDataEntity>>> {
        return cartSubject.listen()
    }

    override suspend fun addToCart(productId: Long, quantity: Int) {
        cartDataSource.saveToCart(productId, quantity)
        notifyChanges()
    }

    override suspend fun getCartItemById(id: Long): CartItemDataEntity {
        return cartDataSource.getCart().firstOrNull { it.id == id } ?: throw NotFoundException()
    }

    override suspend fun deleteCartItem(ids: List<Long>) {
        ids.forEach { cartDataSource.delete(it) }
        cartSubject.newAsyncLoad(silently = true)
    }

    override suspend fun deleteAll() {
        cartDataSource.deleteAll()
        cartSubject.newAsyncLoad(silently = true)
    }

    override suspend fun changeQuantity(cartId: Long, quantity: Int) {
        val cartItem = getCartItemById(cartId)
        val productId = cartItem.productId
        cartDataSource.saveToCart(productId, quantity)
        cartSubject.newAsyncLoad(silently = true)
    }

    override fun reload() {
        cartSubject.newAsyncLoad()
    }

    private suspend fun notifyChanges() {
        cartSubject.updateWith(Container.Success(cartDataSource.getCart()))
    }

}