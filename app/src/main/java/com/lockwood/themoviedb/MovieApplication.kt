package com.lockwood.themoviedb

import android.app.Application
import android.content.Context
import com.lockwood.core.cache.di.DaggerCacheApplication
import com.lockwood.core.cache.di.component.DaggerCacheComponent
import com.lockwood.core.cache.di.provider.CacheToolsProvider
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.component.DaggerCoreComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.component.DaggerNetworkComponent
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import timber.log.Timber

class MovieApplication : Application(), DaggerApplication, DaggerNetworkApplication,
    DaggerCacheApplication {

    companion object {

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieApplication).coreComponent

        @JvmStatic
        fun networkComponent(context: Context) =
            (context.applicationContext as MovieApplication).networkComponent

        @JvmStatic
        fun cacheComponent(context: Context) =
            (context.applicationContext as MovieApplication).cacheComponent

    }

    private val coreComponent: AppToolsProvider by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

    private val networkComponent: NetworkToolsProvider by lazy {
        DaggerNetworkComponent.builder()
            .application(this)
            .build()
    }

    private val cacheComponent: CacheToolsProvider by lazy {
        DaggerCacheComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    override fun getAppToolsProvider(): AppToolsProvider {
        return coreComponent(this)
    }

    override fun getNetworkToolsProvider(): NetworkToolsProvider {
        return networkComponent(this)
    }

    override fun getCacheToolsProvider(): CacheToolsProvider {
        return cacheComponent(this)
    }

}