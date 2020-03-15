package com.lockwood.core.network.di.module

import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.di.qualifier.BaseUrl
import com.lockwood.themoviedb.core.network.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @ApiKey
    fun provideApiKey(): String {
        return BuildConfig.MOVIES_API_KEY
    }

//    @Provides
//    @RequestToken
//    fun provideRequestToken(sharedPreferences: SharedPreferences): String {
//    }
//
//    @Provides
//    @SessionId
//    fun provideSessionId(sharedPreferences: SharedPreferences): String {
//    }

}