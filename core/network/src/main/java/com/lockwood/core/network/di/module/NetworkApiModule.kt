package com.lockwood.core.network.di.module

import com.lockwood.core.network.BuildConfig
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkApiModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String {
        return BuildConfig.MOVIES_API_KEY
    }

}