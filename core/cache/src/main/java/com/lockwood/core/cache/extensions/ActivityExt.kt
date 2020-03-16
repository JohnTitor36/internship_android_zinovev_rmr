package com.lockwood.core.cache.extensions

import android.app.Activity
import com.lockwood.core.cache.di.DaggerCacheApplication
import com.lockwood.core.cache.di.provider.CacheToolsProvider

val Activity.cacheToolsProvider: CacheToolsProvider
    get() = (applicationContext as DaggerCacheApplication).getCacheToolsProvider()