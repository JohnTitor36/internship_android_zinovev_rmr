package com.lockwood.core.preferences.user

interface UserPreferences {

    // TODO: добавить username
    // TODO: добавить email

    fun isUserLoggedIn(): Boolean

    fun setUserLoggedIn(value: Boolean)

}