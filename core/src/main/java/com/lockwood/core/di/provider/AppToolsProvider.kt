package com.lockwood.core.di.provider

import com.lockwood.core.reader.ResourceReader

interface AppToolsProvider : ApplicationProvider {

    fun provideResourceReader(): ResourceReader

}