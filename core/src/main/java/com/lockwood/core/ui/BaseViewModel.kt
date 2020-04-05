package com.lockwood.core.ui

import androidx.lifecycle.ViewModel
import com.lockwood.core.event.EventsQueue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val eventsQueue by lazy { EventsQueue() }

    private val compositeDisposable by lazy { CompositeDisposable() }

    open fun handleError(throwable: Throwable) = Unit

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun Disposable.autoDispose(): Disposable {
        compositeDisposable.add(this)
        return this
    }

}