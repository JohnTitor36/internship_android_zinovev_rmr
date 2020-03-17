package com.lockwood.core.preferences.extensions

import android.content.SharedPreferences
import com.lockwood.core.preferences.preference.Preference

object DelegatesExt {

    fun <T> preference(
        prefs: SharedPreferences,
        name: String,
        default: T
    ) = Preference(prefs, name, default)

}

