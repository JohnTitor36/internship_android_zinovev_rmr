package com.lockwood.themoviedb

import android.app.Application
import android.content.Context
import com.lockwood.core.di.component.CoreComponent
import com.lockwood.core.di.component.DaggerCoreComponent

class MovieApplication : Application() {

    companion object {

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieApplication).coreComponent
    }

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }

}