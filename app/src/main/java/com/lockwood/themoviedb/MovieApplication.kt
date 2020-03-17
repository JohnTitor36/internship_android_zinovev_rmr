package com.lockwood.themoviedb

import android.app.Application
import android.content.Context
import com.lockwood.core.preferences.di.DaggerPreferencesApplication
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.component.DaggerCoreComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.component.DaggerNetworkComponent
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.component.DaggerPreferencesComponent
import timber.log.Timber

class MovieApplication : Application(), DaggerApplication, DaggerNetworkApplication,
    DaggerPreferencesApplication {

    companion object {

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieApplication).coreComponent

        @JvmStatic
        fun preferencesComponent(context: Context) =
            (context.applicationContext as MovieApplication).preferencesComponent

        @JvmStatic
        fun networkComponent(context: Context) =
            (context.applicationContext as MovieApplication).networkComponent

    }

    private val coreComponent: AppToolsProvider by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

    private val preferencesComponent: PreferencesToolsProvider by lazy {
        DaggerPreferencesComponent.builder()
            .application(this)
            .build()
    }

    private val networkComponent: NetworkToolsProvider by lazy {
        DaggerNetworkComponent.builder()
            .application(this)
            .preferencesToolsProvider(preferencesComponent)
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

    override fun getPreferencesToolsProvider(): PreferencesToolsProvider {
        return preferencesComponent(this)
    }

}