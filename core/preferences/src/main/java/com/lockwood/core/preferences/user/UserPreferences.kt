package com.lockwood.core.preferences.user

interface UserPreferences {

    fun isUserLoggedIn(): Boolean

    fun setUserLoggedIn(value: Boolean)

}