package com.kbcoding.commonimpl

import android.util.Log
import com.kbcoding.common.Logger

/**
 * Default implementation of [Logger] which sends all logs to the LogCat.
 */
class LoggerImpl : Logger {

    override fun log(message: String) {
        Log.d("AndroidLogger", message)
    }

    override fun err(exception: Throwable, message: String?) {
        Log.e("AndroidLogger", message, exception)
    }

}