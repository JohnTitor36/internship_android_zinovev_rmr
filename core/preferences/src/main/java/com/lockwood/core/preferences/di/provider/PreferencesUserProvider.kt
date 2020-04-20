package com.lockwood.core.preferences.di.provider

import com.lockwood.core.preferences.di.qualifier.AccountId
import com.lockwood.core.preferences.user.UserPreferences

interface PreferencesUserProvider {

    fun provideUserPreferences(): UserPreferences

    @AccountId
    fun provideAccountId(): String

}