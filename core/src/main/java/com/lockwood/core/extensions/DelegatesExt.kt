package com.lockwood.core.extensions

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.lockwood.core.cache.preferences.Preference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object DelegatesExt {

    fun <T> preference(
        prefs: SharedPreferences,
        name: String,
        default: T
    ) = Preference(prefs, name, default)
}

