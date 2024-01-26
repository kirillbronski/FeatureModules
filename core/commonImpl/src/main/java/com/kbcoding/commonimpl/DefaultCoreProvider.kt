package com.kbcoding.commonimpl

import android.content.Context
import com.kbcoding.common.AppRestarter
import com.kbcoding.common.CommonUi
import com.kbcoding.common.CoreProvider
import com.kbcoding.common.ErrorHandler
import com.kbcoding.common.Logger
import com.kbcoding.common.Resources
import com.kbcoding.common.ScreenCommunication
import kotlinx.coroutines.CoroutineScope

class DefaultCoreProvider(
    private val appContext: Context,
    override val appRestarter: AppRestarter,
    override val commonUi: CommonUi = AndroidCommonUi(appContext),
    override val resources: Resources = AndroidResources(appContext),
    override val screenCommunication: ScreenCommunication = DefaultScreenCommunication(),
    override val globalScope: CoroutineScope = createDefaultGlobalScope(),
    override val logger: Logger = AndroidLogger(),
    override val errorHandler: ErrorHandler = DefaultErrorHandler(
        logger, commonUi, resources, appRestarter, globalScope
    )
) : CoreProvider