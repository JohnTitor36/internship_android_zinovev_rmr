package com.lockwood.core.cache.di.module

import android.content.Context
import com.lockwood.core.cache.di.DaggerCacheApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Singleton
    @Provides
    fun provideApplicationContext(application: DaggerCacheApplication): Context {
        return application.getApplicationContext()
    }

}