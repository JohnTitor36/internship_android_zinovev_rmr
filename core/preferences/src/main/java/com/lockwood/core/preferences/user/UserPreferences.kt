package com.lockwood.core.preferences.user

interface UserPreferences {

    // TODO: добавить username
    // TODO: добавить email

    fun fetchUserLoggedIn(): Boolean

    fun setUserLoggedIn(value: Boolean)

}