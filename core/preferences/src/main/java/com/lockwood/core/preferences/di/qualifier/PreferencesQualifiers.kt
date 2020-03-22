package com.lockwood.core.preferences.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EncryptedPreferences

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Preferences