package com.lockwood.core.cache.extensions

import androidx.fragment.app.Fragment
import com.lockwood.core.cache.di.provider.CacheToolsProvider

val Fragment.cacheToolsProvider: CacheToolsProvider
    get() = requireActivity().cacheToolsProvider