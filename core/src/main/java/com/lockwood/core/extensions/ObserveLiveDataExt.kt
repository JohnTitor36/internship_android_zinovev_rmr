package com.lockwood.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T, LD : LiveData<T>> Fragment.observe(
    liveData: LD,
    observer: Observer<T>
) {
    liveData.observe(viewLifecycleOwner, observer)
}

inline fun <T, LD : LiveData<T>> Fragment.observe(
    liveData: LD,
    crossinline block: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner, Observer { block(it) })
}