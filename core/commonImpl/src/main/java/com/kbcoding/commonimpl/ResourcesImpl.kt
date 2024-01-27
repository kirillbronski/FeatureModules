package com.kbcoding.commonimpl

import android.content.Context
import com.kbcoding.common.Resources


/**
 * Default implementation of [Resources] which fetches strings from an application context.
 */
class ResourcesImpl(
    private val applicationContext: Context,
) : Resources {

    override fun getString(id: Int): String {
        return applicationContext.getString(id)
    }

    override fun getString(id: Int, vararg placeholders: Any): String {
        return applicationContext.getString(id, placeholders)
    }

}