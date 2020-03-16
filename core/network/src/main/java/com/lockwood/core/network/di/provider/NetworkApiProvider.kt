package com.lockwood.core.network.di.provider

import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.di.qualifier.BaseUrl

interface NetworkApiProvider {

    @BaseUrl
    fun provideBaseUrl(): String

    @ApiKey
    fun provideApiKey(): String

}