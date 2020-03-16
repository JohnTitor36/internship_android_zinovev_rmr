package com.lockwood.core.cache.di.provider

import android.content.SharedPreferences
import com.lockwood.core.cache.di.qualifier.EncryptedPreferences
import com.lockwood.core.cache.di.qualifier.Preferences

interface PreferencesProvider {

    @EncryptedPreferences
    fun provideEncryptedSharedPreferences(): SharedPreferences

    @Preferences
    fun provideSharedPreferences(): SharedPreferences

}