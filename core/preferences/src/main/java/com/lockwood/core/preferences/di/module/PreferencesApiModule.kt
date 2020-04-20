package com.lockwood.core.preferences.di.module

import android.content.SharedPreferences
import com.lockwood.core.cryptographic.Cryptographer
import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.core.preferences.authentication.DefaultAuthenticationPreferences
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
    fun provideAuthenticationCache(
        @EncryptedPreferences sharedPreferences: SharedPreferences,
        cryptographer: Cryptographer
    ): AuthenticationPreferences {
        return DefaultAuthenticationPreferences(sharedPreferences, cryptographer)
    }

    @Provides
    @RequestToken
    fun provideRequestToken(authentication: AuthenticationPreferences): String {
        return authentication.fetchCurrentRequestToken()
    }

    @Provides
    @SessionId
    fun provideSessionId(authentication: AuthenticationPreferences): String {
        return authentication.fetchCurrentSessionId()
    }

}