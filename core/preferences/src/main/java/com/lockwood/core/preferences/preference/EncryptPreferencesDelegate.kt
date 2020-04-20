package com.lockwood.core.preferences.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import com.lockwood.core.cryptographic.Cryptographer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class EncryptPreferencesDelegate<T>(
    private val cryptographer: Cryptographer,
    private val prefs: SharedPreferences,
    private val name: String,
    private val default: T
) : ReadWriteProperty<Any?, T> {

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T {
        return prefs.findPreference(name, default)
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T
    ) {
        prefs.putPreference(name, value)
    }

    private fun SharedPreferences.findPreference(
        name: String,
        default: T
    ): T {
        val cipherText = getString(name, null)
        return if (cipherText == null) {
            default
        } else {
            val plainText = cryptographer.decryptString(cipherText)
            plainText.toType()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> String.toType(): T {
        val value = when (default) {
            is Long -> toLong()
            is String -> toString()
            is Int -> toInt()
            is Boolean -> toBoolean()
            is Float -> toFloat()
            else -> throw IllegalArgumentException("Этот тип не может быть сохранен в Preferences")
        }
        return value as T
    }

    private fun SharedPreferences.putPreference(
        name: String,
        value: T
    ) {
        val plainText = value.toString()
        val cipherText = cryptographer.encryptString(plainText)
        edit { putString(name, cipherText) }
    }

}

fun SharedPreferences.encryptDelegate(
    name: String,
    default: String,
    cryptographer: Cryptographer
) = EncryptPreferencesDelegate(cryptographer, this, name, default)