package com.lockwood.core.preferences.extensions

import android.app.Activity
import androidx.fragment.app.Fragment
import com.lockwood.core.preferences.di.DaggerPreferencesApplication
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider

val Activity.preferencesToolsProvider: PreferencesToolsProvider
    get() = (applicationContext as DaggerPreferencesApplication).getPreferencesToolsProvider()

val Fragment.preferencesToolsProvider: PreferencesToolsProvider
    get() = requireActivity().preferencesToolsProvider