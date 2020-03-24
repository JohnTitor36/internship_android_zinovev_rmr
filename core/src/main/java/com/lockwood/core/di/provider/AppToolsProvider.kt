package com.lockwood.core.di.provider

import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.router.LoginActivityRouter

interface AppToolsProvider : ApplicationProvider {

    fun provideResourceReader(): ResourceReader

    fun provideLoginActivityRouter(): LoginActivityRouter

}