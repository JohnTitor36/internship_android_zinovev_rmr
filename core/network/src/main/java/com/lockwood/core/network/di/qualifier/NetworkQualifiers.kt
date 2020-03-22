package com.lockwood.core.network.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ErrorInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HeaderInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ToLoginAuthenticator