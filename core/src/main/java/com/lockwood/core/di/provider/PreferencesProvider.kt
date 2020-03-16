package com.lockwood.core.di.provider

import android.content.SharedPreferences
import com.lockwood.core.di.qualifier.EncryptedPreferences
import com.lockwood.core.di.qualifier.Preferences

interface PreferencesProvider {

    @EncryptedPreferences
    fun provideEncryptedSharedPreferences(): SharedPreferences

    @Preferences
    fun provideSharedPreferences(): SharedPreferences

}