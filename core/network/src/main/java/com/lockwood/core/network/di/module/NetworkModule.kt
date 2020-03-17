package com.lockwood.core.network.di.module

import android.content.Context
import com.lockwood.core.network.BuildConfig
import com.lockwood.core.network.authenticator.UserToLoginAuthenticator
import com.lockwood.core.network.di.qualifier.ErrorInterceptor
import com.lockwood.core.network.di.qualifier.HeaderInterceptor
import com.lockwood.core.network.di.qualifier.LoggingInterceptor
import com.lockwood.core.network.interceptor.OkHttpErrorInterceptor
import com.lockwood.core.network.interceptor.OkHttpHeaderInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {

        private const val LOG_TAG_OK_HTTP = "OkHttp"
    }

    // Ловим 401 через Authenticator
    @Provides
    @Singleton
    fun provideTokenAuthenticator(context: Context): Authenticator {
        return UserToLoginAuthenticator(context)
    }

    // Ловим остальные сетевые ошибки через отдельный Interceptor
    @Provides
    @Singleton
    @ErrorInterceptor
    fun provideErrorInterceptor(context: Context): Interceptor {
        return OkHttpErrorInterceptor(context)
    }

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag(LOG_TAG_OK_HTTP).d(message)
            }
        })
        // Чтобы быть уверенными, что утечки чувствительных данных не будет
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor(): Interceptor {
        return OkHttpHeaderInterceptor()
    }

}