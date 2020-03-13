package com.lockwood.core.extensions

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