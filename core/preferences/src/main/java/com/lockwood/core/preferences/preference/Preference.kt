package com.lockwood.core.preferences.preference

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(
    private val prefs: SharedPreferences,
    private val name: String,
    private val default: T
) : ReadWriteProperty<Any?, T> {

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T = prefs.findPreference(name, default)

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T
    ) {
        prefs.putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun SharedPreferences.findPreference(
        name: String,
        default: T
    ): T {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Этот тип не может быть сохранен в Preferences")
        }
        return res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun SharedPreferences.putPreference(
        name: String,
        value: T
    ) = with(edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("Этот тип не может быть сохранен в Preferences")
        }.apply()
    }

}