package com.lockwood.core.preferences.di.provider

import com.lockwood.core.preferences.user.UserPreferences

interface PreferencesToolsProvider : PreferencesApiProvider, PreferencesProvider {

    fun provideUserPreferences(): UserPreferences

}