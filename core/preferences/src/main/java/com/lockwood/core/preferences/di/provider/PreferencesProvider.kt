package com.lockwood.core.preferences.di.provider

import android.content.SharedPreferences
import com.lockwood.core.preferences.di.qualifier.EncryptedPreferences
import com.lockwood.core.preferences.di.qualifier.Preferences

interface PreferencesProvider {

    @EncryptedPreferences
    fun provideEncryptedSharedPreferences(): SharedPreferences

    @Preferences
    fun provideSharedPreferences(): SharedPreferences

}