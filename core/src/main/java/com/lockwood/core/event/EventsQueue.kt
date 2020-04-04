package com.lockwood.core.event

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*

interface Event

/**
 * Класс-очередь для обработки временных событий, не являющихся частью View
 * Например, показ SnackBar с сообщением или ошибкой.
 */
class EventsQueue : MutableLiveData<Queue<Event>>() {

    @MainThread
    fun offer(event: Event) {
        val queue = (value ?: LinkedList()).apply {
            add(event)
        }
        value = queue
    }

}

/**
 * Подписка на [LiveData] с очередью одноразовых событий (например, показы снэкбаров и диалогов).
 * @see EventsQueue
 */
fun Fragment.observe(eventsQueue: EventsQueue, eventHandler: (Event) -> Unit) {
    eventsQueue.observe(viewLifecycleOwner, Observer { queue: Queue<Event>? ->
        while (queue != null && queue.isNotEmpty()) {
            eventHandler(queue.remove())
        }
    })
}