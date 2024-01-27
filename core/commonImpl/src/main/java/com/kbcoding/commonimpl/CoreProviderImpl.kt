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

class CoreProviderImpl(
    private val appContext: Context,
    override val appRestarter: AppRestarter,
    override val commonUi: CommonUi = CommonUiImpl(appContext),
    override val resources: Resources = ResourcesImpl(appContext),
    override val screenCommunication: ScreenCommunication = DefaultScreenCommunication(),
    override val globalScope: CoroutineScope = createDefaultGlobalScope(),
    override val logger: Logger = LoggerImpl(),
    override val errorHandler: ErrorHandler = ErrorHandlerImpl(
        logger, commonUi, resources, appRestarter, globalScope
    )
) : CoreProvider