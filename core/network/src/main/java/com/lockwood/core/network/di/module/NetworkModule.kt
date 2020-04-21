package com.lockwood.core.network.di.module

import com.lockwood.core.network.BuildConfig
import com.lockwood.core.network.authenticator.UserToLoginAuthenticator
import com.lockwood.core.network.di.qualifier.*
import com.lockwood.core.network.extensions.addInterceptors
import com.lockwood.core.network.extensions.commonTimeout
import com.lockwood.core.network.interceptor.HttpApiKeyInterceptor
import com.lockwood.core.network.interceptor.HttpErrorInterceptor
import com.lockwood.core.network.interceptor.HttpHeaderInterceptor
import com.lockwood.core.network.interceptor.HttpSessionInterceptor
import com.lockwood.core.network.manager.ConnectivityManager
import com.lockwood.core.network.moshi.adapter.*
import com.lockwood.core.preferences.di.qualifier.SessionId
import com.lockwood.core.window.WindowManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.CertificatePinner
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
        connectivityManager: ConnectivityManager,
        moshi: Moshi.Builder
    ): Interceptor {
        return HttpErrorInterceptor(connectivityManager, moshi.build())
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
//            logging.level = HttpLoggingInterceptor.Level.BODY
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
    @SessionInterceptor
    fun provideSessionInterceptor(@SessionId id: String): Interceptor {
        return HttpSessionInterceptor(id)
    }

    @Provides
    @Singleton
    @HeaderInterceptor
    fun provideHeaderInterceptor(): Interceptor {
        return HttpHeaderInterceptor()
    }

    @Provides
    fun provideMoshi(windowManager: WindowManager): Moshi.Builder {
        val builder = Moshi.Builder().add(KotlinJsonAdapterFactory())

        val adapters = arrayOf(
            DateAdapter(),
            LanguageAdapter(),
            GravatarUrlAdapter(windowManager),
            PosterAdapter(windowManager),
            BackdropAdapter(windowManager)
        )

        adapters.forEach { builder.add(it) }
        return builder
    }

    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add("www.themoviedb.org", "sha256/HkCBucsA3Tgiby96X7vjb/ojHaE1BrjvZ2+LRdJJd0E=")
            .add("api.themoviedb.org", "sha256/HkCBucsA3Tgiby96X7vjb/ojHaE1BrjvZ2+LRdJJd0E=")
            .build()
    }

    @Provides
    fun provideOkHttpClientBuilder(
        certificatePinner: CertificatePinner,
        @ApiKeyInterceptor apiKeyInterceptor: Interceptor,
        @HeaderInterceptor headerInterceptor: Interceptor,
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @ErrorInterceptor errorInterceptor: Interceptor
    ): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .certificatePinner(certificatePinner)
            .commonTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptors(
                apiKeyInterceptor,
                headerInterceptor,
                loggingInterceptor,
                errorInterceptor
            )
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
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi.Builder
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi.build()))
            .client(okHttpClient)
    }

    @Provides
    @AuthRetrofit
    fun provideAuthRetrofit(
        retrofit: Retrofit.Builder,
        @AuthHttpClient okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return retrofit.client(okHttpClient)
    }

}