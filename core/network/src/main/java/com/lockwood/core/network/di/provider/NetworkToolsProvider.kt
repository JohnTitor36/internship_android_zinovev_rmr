package com.lockwood.core.network.di.provider

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkToolsProvider : NetworkApiProvider {

    fun provideMoshi(): Moshi

    fun provideRetrofit(): Retrofit

    fun provideOkHttpClient(): OkHttpClient

}