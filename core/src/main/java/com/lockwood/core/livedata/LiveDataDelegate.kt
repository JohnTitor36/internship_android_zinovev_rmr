package com.lockwood.core.livedata

import androidx.lifecycle.MutableLiveData
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : Any> MutableLiveData<T>.delegate(): ReadWriteProperty<Any, T> {

    return object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T = requireValue()
        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = onNext(value)
    }

}