package com.lockwood.core.preferences.extensions

import androidx.fragment.app.Fragment
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider

val Fragment.preferencesToolsProvider: PreferencesToolsProvider
    get() = requireActivity().preferencesToolsProvider