package com.lockwood.core.di.module

import android.content.Context
import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.schedulers.AndroidSchedulersProvider
import com.lockwood.core.schedulers.SchedulersProvider
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
    fun provideSchedulers(): SchedulersProvider {
        return AndroidSchedulersProvider()
    }

}