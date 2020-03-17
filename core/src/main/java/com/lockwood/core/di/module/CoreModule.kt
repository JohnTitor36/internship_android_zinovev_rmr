package com.lockwood.core.di.module

import android.content.Context
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.snackbar.SnackbarMaker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideApplicationContext(application: DaggerApplication): Context {
        return application.getApplicationContext()
    }

    @Provides
    @Singleton
    fun provideSnackbarMaker(context: Context): SnackbarMaker {
        return SnackbarMaker(context)
    }

}