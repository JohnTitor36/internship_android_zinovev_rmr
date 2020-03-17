package com.lockwood.core.preferences.authentication

interface AuthenticationPreferences {

    fun fetchCurrentRequestToken(): String

    fun fetchCurrentSessionId(): Int

    fun saveCurrentRequestToken(value: String)

    fun saveCurrentSessionId(value: Int)

}