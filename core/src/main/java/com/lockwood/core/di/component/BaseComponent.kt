package com.lockwood.core.di.component

interface BaseComponent<T> {

    fun inject(target: T)
}