package com.lockwood.core.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.getViewModel(factory: ViewModelProvider.Factory? = null): T =
    if (factory == null) {
        ViewModelProvider(this).get(T::class.java)
    } else {
        ViewModelProvider(this, factory).get(T::class.java)
    }

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(factory: ViewModelProvider.Factory? = null): T =
    if (factory == null) {
        ViewModelProvider(this).get(T::class.java)
    } else {
        ViewModelProvider(this, factory).get(T::class.java)
    }