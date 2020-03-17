package com.lockwood.core.extensions

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.Event

fun MutableLiveData<Event<Unit>>.invoke() {
    value = Event(Unit)
}