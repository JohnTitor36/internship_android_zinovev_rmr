package com.lockwood.core.preferences.di.module

import android.content.SharedPreferences
import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.core.preferences.authentication.DefaultAuthenticationPreferences
import com.lockwood.core.preferences.di.qualifier.EncryptedPreferences
import com.lockwood.core.preferences.di.qualifier.Preferences
import com.lockwood.core.preferences.di.qualifier.RequestToken
import com.lockwood.core.preferences.di.qualifier.SessionId
import com.lockwood.core.preferences.user.DefaultUserPreferences
import com.lockwood.core.preferences.user.UserPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesApiModule {

    @Provides
    @Singleton
    fun provideAuthenticationCache(@EncryptedPreferences sharedPreferences: SharedPreferences): AuthenticationPreferences {
        return DefaultAuthenticationPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideUserCache(@Preferences sharedPreferences: SharedPreferences): UserPreferences {
        return DefaultUserPreferences(sharedPreferences)
    }

    @Provides
    @RequestToken
    fun provideRequestToken(authentication: AuthenticationPreferences): String {
        return authentication.fetchCurrentRequestToken()
    }

    @Provides
    @SessionId
    fun provideSessionId(authentication: AuthenticationPreferences): Int {
        return authentication.fetchCurrentSessionId()
    }

}