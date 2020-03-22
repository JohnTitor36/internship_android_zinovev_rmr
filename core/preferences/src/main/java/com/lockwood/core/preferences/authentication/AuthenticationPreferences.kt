package com.lockwood.core.preferences.authentication

interface AuthenticationPreferences {

    fun fetchCurrentRequestToken(): String

    fun fetchCurrentSessionId(): String

    fun saveCurrentRequestToken(value: String)

    fun saveCurrentSessionId(value: String)

    fun resetCurrentRequestToken()

    fun resetCurrentSessionId()

}