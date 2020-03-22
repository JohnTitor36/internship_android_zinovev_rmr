package com.lockwood.core.preferences.di

import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider

interface DaggerPreferencesApplication {

    fun getPreferencesToolsProvider(): PreferencesToolsProvider

}