package com.lockwood.core.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.lockwood.core.event.EventsQueue
import com.lockwood.core.event.NavigationEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val eventsQueue by lazy { EventsQueue() }

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    @CallSuper
    open fun handleError(throwable: Throwable) {
        Timber.e(throwable)
    }

    protected fun navigateTo(
        direction: NavDirections,
        navOptions: NavOptions? = null
    ) {
        val event = NavigationEvent(direction, navOptions)
        eventsQueue.offer(event)
    }

    protected fun Disposable.autoDispose(): Disposable {
        compositeDisposable.add(this)
        return this
    }

}