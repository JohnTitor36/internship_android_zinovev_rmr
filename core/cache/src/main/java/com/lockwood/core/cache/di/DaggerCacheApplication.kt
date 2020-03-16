package com.lockwood.core.cache.di

import android.content.Context
import com.lockwood.core.cache.di.provider.CacheToolsProvider

interface DaggerCacheApplication {

    fun getApplicationContext(): Context

    fun getCacheToolsProvider(): CacheToolsProvider

}