package com.lockwood.core.preferences.di

import android.content.Context
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider

interface DaggerPreferencesApplication {

    fun getApplicationContext(): Context

    fun getPreferencesToolsProvider(): PreferencesToolsProvider

}