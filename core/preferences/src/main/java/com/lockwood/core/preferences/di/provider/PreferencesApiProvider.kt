package com.lockwood.core.preferences.di.provider

import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.core.preferences.di.qualifier.RequestToken
import com.lockwood.core.preferences.di.qualifier.SessionId

interface PreferencesApiProvider {

    fun provideAuthenticationPreferences(): AuthenticationPreferences

    @RequestToken
    fun provideRequestToken(): String

    @SessionId
    fun provideSessionId(): String

}