package com.lockwood.core.cache.di.module

import android.content.SharedPreferences
import com.lockwood.core.cache.authentication.AuthenticationCache
import com.lockwood.core.cache.authentication.DefaultAuthenticationCache
import com.lockwood.core.cache.di.qualifier.EncryptedPreferences
import com.lockwood.core.cache.di.qualifier.RequestToken
import com.lockwood.core.cache.di.qualifier.SessionId
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheApiModule {

    @Provides
    @Singleton
    fun provideAuthenticationCache(@EncryptedPreferences sharedPreferences: SharedPreferences): AuthenticationCache {
        return DefaultAuthenticationCache(sharedPreferences)
    }

    @Provides
    @RequestToken
    fun provideRequestToken(authentication: AuthenticationCache): String {
        return authentication.fetchCurrentRequestToken()
    }

    @Provides
    @SessionId
    fun provideSessionId(authentication: AuthenticationCache): Int {
        return authentication.fetchCurrentSessionId()
    }

}