package com.lockwood.core.network.di.provider

import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkToolsProvider : NetworkApiProvider {

    fun provideMoshi(): Moshi.Builder

    fun provideOkHttpClientBuilder(): OkHttpClient.Builder

    fun provideOkHttpClient(): OkHttpClient

    fun provideRetrofit(): Retrofit.Builder

    @AuthRetrofit
    fun provideAuthRetrofit(): Retrofit.Builder

    fun provideNetworkConnectivityManager(): NetworkConnectivityManager

}