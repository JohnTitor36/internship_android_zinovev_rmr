package com.lockwood.core.network.di.module

import android.content.Context
import com.lockwood.core.network.di.DaggerNetworkApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Singleton
    @Provides
    fun provideApplicationContext(application: DaggerNetworkApplication): Context {
        return application.getApplicationContext()
    }

}