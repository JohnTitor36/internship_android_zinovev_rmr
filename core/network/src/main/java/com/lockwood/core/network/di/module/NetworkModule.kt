package com.lockwood.core.network.di.module

import android.content.Context
import com.lockwood.core.network.authenticator.UserToLoginAuthenticator
import com.lockwood.core.network.di.qualifier.LoggingInterceptor
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

    @Provides
    @Singleton
    fun provideTokenAuthenticator(context: Context): Authenticator {
        return UserToLoginAuthenticator(context)
    }

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(context: Context): Interceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag(LOG_TAG_OK_HTTP).d(message)
            }
        })
    }

}