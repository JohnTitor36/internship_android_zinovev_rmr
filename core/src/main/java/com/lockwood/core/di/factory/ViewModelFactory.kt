package com.lockwood.core.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

typealias MapViewModelProviders = Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val providers: MapViewModelProviders
) : ViewModelProvider.Factory {

    private fun <T> MapViewModelProviders.findByModel(modelClass: Class<T>): @JvmSuppressWildcards Provider<ViewModel>? {
        return asIterable().find { modelClass.isAssignableFrom(it.key) }?.value
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = providers[modelClass] ?: providers.findByModel(modelClass)
        ?: error("Unknown ViewModel class $modelClass")
        return try {
            provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}