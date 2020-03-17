package com.lockwood.core.preferences.di.module

import android.content.SharedPreferences
import com.lockwood.core.preferences.authentication.AuthenticationCache
import com.lockwood.core.preferences.authentication.DefaultAuthenticationCache
import com.lockwood.core.preferences.di.qualifier.EncryptedPreferences
import com.lockwood.core.preferences.di.qualifier.RequestToken
import com.lockwood.core.preferences.di.qualifier.SessionId
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesApiModule {

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