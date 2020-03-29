package com.lockwood.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.Event
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

fun MutableLiveData<Event<Unit>>.invoke() {
    value = Event(Unit)
}

fun <T> MutableLiveData<T>.onNext(next: T) {
    Timber.d("onNext: $next")
    value = next
}

fun <T : Any> MutableLiveData<T>.delegate(): ReadWriteProperty<Any, T> {

    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T = requireValue()
        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = onNext(value)
    }
}