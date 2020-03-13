package com.lockwood.core.di.provider

import android.content.Context

interface ApplicationProvider {

    fun provideAppContext(): Context
}