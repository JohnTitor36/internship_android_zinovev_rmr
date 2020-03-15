package com.lockwood.core.di.provider

import android.content.Context

interface ApplicationProvider {

    fun provideApplicationContext(): Context

}