package com.lockwood.core.preferences.di.module

import android.content.Context
import com.lockwood.core.preferences.di.DaggerPreferencesApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Singleton
    @Provides
    fun provideApplicationContext(application: DaggerPreferencesApplication): Context {
        return application.getApplicationContext()
    }

}