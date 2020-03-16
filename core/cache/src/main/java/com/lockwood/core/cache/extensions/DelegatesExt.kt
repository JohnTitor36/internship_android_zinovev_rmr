package com.lockwood.core.cache.extensions

import android.content.SharedPreferences
import com.lockwood.core.cache.preference.Preference

object DelegatesExt {

    fun <T> preference(
        prefs: SharedPreferences,
        name: String,
        default: T
    ) = Preference(prefs, name, default)

}

