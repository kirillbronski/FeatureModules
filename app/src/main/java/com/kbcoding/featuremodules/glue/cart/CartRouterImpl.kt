package com.kbcoding.featuremodules.glue.cart

import com.kbcoding.catalog.presentation.details.ProductDetailsFragment
import com.kbcoding.common.ScreenCommunication
import com.kbcoding.common.listen
import com.kbcoding.featuremodules.R
import com.kbcoding.features.cart.presentation.quantity.EditQuantityDialogFragment
import com.kbcoding.features.cart.presentation.quantity.EditQuantityResult
import com.kbcoding.navigation.GlobalNavComponentRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ua.cn.stu.multimodule.cart.presentation.CartRouter
import javax.inject.Inject

class CartRouterImpl @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
    private val screenCommunication: ScreenCommunication,
) : CartRouter {

    override fun launchProductDetails(productId: Long) {
        globalNavComponentRouter.launch(
            R.id.productDetailsFragment,
            ProductDetailsFragment.Screen(productId)
        )
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun launchEditQuantity(cartItemId: Long, initialQty: Int) {
        globalNavComponentRouter.launch(
            R.id.editQuantityDialogFragment,
            EditQuantityDialogFragment.Screen(cartItemId, initialQty)
        )
    }

    override fun launchCreateOrder() {
        globalNavComponentRouter.launch(R.id.createOrderFragment)
    }

    override fun receiveQuantity(): Flow<EditQuantityResult> {
        return screenCommunication.listen(EditQuantityResult::class.java)
    }

    override fun sendQuantity(editQuantityResult: EditQuantityResult) {
        screenCommunication.publishResult(editQuantityResult)
    }

    override fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean) {
        globalNavComponentRouter.registerBackHandler(scope, handler)
    }

}