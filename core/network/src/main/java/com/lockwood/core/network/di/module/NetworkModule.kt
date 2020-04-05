package com.lockwood.core.network.di.module

import com.lockwood.core.network.BuildConfig
import com.lockwood.core.network.authenticator.UserToLoginAuthenticator
import com.lockwood.core.network.di.qualifier.*
import com.lockwood.core.network.interceptor.HttpApiKeyInterceptor
import com.lockwood.core.network.interceptor.HttpErrorInterceptor
import com.lockwood.core.network.interceptor.HttpHeaderInterceptor
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.network.moshi.adapter.*
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {

        private const val LOG_TAG_OK_HTTP = "OkHttp"

        private const val DEFAULT_TIMEOUT_SECONDS = 30L
    }

    @Provides
    @Singleton
    fun provideAuthenticator(
        authenticator: UserToLoginAuthenticator
    ): Authenticator {
        return authenticator
    }

    @Provides
    @Singleton
    @ErrorInterceptor
    fun provideErrorInterceptor(
        connectivityManager: NetworkConnectivityManager,
        moshi: Moshi
    ): Interceptor {
        return HttpErrorInterceptor(connectivityManager, moshi)
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
//            logging.level = HttpLoggingInterceptor.Level.BASIC
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return logging
    }

    @Provides
    @Singleton
    @ApiKeyInterceptor
    fun provideApiKeyInterceptor(@ApiKey key: String): Interceptor {
        return HttpApiKeyInterceptor(key)
    }

    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor(): Interceptor {
        return HttpHeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideMoshi(windowManager: WindowManager): Moshi {
        return Moshi.Builder()
            .add(DateAdapter())
            .add(LanguageAdapter())
            .add(GravatarUrlAdapter(windowManager))
            .add(PosterAdapter(windowManager))
            .add(BackdropAdapter(windowManager))
            .build()
    }

    @Provides
    fun provideOkHttpClientBuilder(
        @ApiKeyInterceptor apiKeyInterceptor: Interceptor,
        @HeaderInterceptor headerInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @ErrorInterceptor errorInterceptor: Interceptor
    ): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorInterceptor)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        okHttpClient: OkHttpClient.Builder
    ): OkHttpClient {
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    @AuthHttpClient
    fun provideAuthHttpClient(
        okHttpClient: OkHttpClient.Builder,
        authenticator: Authenticator
    ): OkHttpClient {
        return okHttpClient.authenticator(authenticator).build()
    }

    @Provides
    fun provideRetrofitBuilder(
        @BaseUrl baseUrl: String,
        moshi: Moshi
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofit: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return retrofit.client(okHttpClient).build()
    }

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(
        retrofit: Retrofit.Builder,
        @AuthHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return retrofit.client(okHttpClient).build()
    }

}