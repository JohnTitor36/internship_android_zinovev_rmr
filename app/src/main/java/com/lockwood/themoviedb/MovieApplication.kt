package com.lockwood.themoviedb

import android.app.Application
import android.content.Context
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.component.DaggerCoreComponent
import com.lockwood.core.di.provider.AppToolsProvider
import timber.log.Timber

class MovieApplication : Application(), DaggerApplication {

    companion object {

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieApplication).coreComponent
    }

    private val coreComponent: AppToolsProvider by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    override fun getAppToolsProvider(): AppToolsProvider = coreComponent(this)

}