package com.lockwood.core.preferences.di.module

import android.content.SharedPreferences
import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.core.preferences.di.qualifier.AccountId
import com.lockwood.core.preferences.di.qualifier.Preferences
import com.lockwood.core.preferences.user.DefaultUserPreferences
import com.lockwood.core.preferences.user.UserPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesUserModule {

    @Provides
    @Singleton
    fun provideUserCache(@Preferences sharedPreferences: SharedPreferences): UserPreferences {
        return DefaultUserPreferences(sharedPreferences)
    }

    @Provides
    @AccountId
    fun provideAccountId(authentication: AuthenticationPreferences): String {
        return authentication.fetchCurrentSessionId()
    }

}