package com.lockwood.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lockwood.core.event.Event

fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

fun MutableLiveData<Event<Unit>>.invoke() {
    value = Event(Unit)
}

fun <T> MutableLiveData<T>.onNext(next: T) {
    value = next
}

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