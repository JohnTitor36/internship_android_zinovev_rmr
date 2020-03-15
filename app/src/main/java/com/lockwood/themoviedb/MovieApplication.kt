package com.lockwood.themoviedb

import android.app.Application
import android.content.Context
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.component.DaggerCoreComponent
import com.lockwood.core.di.provider.ApplicationProvider
import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.component.DaggerNetworkComponent
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import timber.log.Timber

class MovieApplication : Application(), DaggerApplication, DaggerNetworkApplication {

    companion object {

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieApplication).coreComponent

        @JvmStatic
        fun networkComponent(context: Context) =
            (context.applicationContext as MovieApplication).networkComponent
    }

    private val coreComponent: ApplicationProvider by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

    private val networkComponent: NetworkToolsProvider by lazy {
        DaggerNetworkComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    override fun getApplicationProvider(): ApplicationProvider {
        return coreComponent(this)
    }

    override fun getNetworkToolsProvider(): NetworkToolsProvider {
        return networkComponent(this)
    }

}