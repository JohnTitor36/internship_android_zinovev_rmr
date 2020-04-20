package com.lockwood.core.preferences.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import com.lockwood.core.cryptographic.Cryptographer
import timber.log.Timber
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class EncryptPreferencesDelegate(
    private val cryptographer: Cryptographer,
    private val prefs: SharedPreferences,
    private val name: String,
    private val default: String
) : ReadWriteProperty<Any?, String> {

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): String {
        val cipherText = prefs.getString(name, null)
        Timber.d("getValue cipherText:$cipherText")
        return if (cipherText == null) {
            default
        } else {
            cryptographer.decryptString(cipherText)
        }
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: String
    ) {
        val cipherText = cryptographer.encryptString(value)
        Timber.d("setValue value:$value")
        Timber.d("setValue cipherText:$cipherText")
        prefs.edit { putString(name, cipherText) }
    }

}

fun SharedPreferences.encryptDelegate(
    name: String,
    default: String,
    cryptographer: Cryptographer
) = EncryptPreferencesDelegate(cryptographer, this, name, default)