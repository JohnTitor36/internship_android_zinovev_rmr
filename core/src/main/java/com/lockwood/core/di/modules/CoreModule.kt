package com.lockwood.core.di.modules

import android.content.Context
import com.lockwood.core.di.DaggerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideApplicationContext(application: DaggerApplication): Context {
        return application.getApplicationContext()
    }

}