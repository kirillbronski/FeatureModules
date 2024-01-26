package com.kbcoding.presentation.assignable

import com.kbcoding.presentation.live.LiveValue
import com.kbcoding.presentation.live.MutableLiveValue

internal class LiveValueAssignable<T>(
    private val liveValue: LiveValue<T>
) : Assignable<T> {

    override fun setValue(value: T) {
        (liveValue as? MutableLiveValue<T>)?.setValue(value)
    }
}