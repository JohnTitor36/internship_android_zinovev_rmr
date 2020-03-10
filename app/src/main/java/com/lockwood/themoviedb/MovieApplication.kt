package com.lockwood.themoviedb

import android.app.Activity
import android.app.Application
import android.content.Context
import com.lockwood.core.di.component.CoreComponent
import com.lockwood.core.di.component.DaggerCoreComponent

class MovieApplication : Application() {

    private val coreComponent: CoreComponent by lazy { DaggerCoreComponent.create() }

    companion object {

        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as MovieApplication).coreComponent
    }

}

val Activity.coreComponent
    get() = MovieApplication.coreComponent(this)