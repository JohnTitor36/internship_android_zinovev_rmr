package com.lockwood.core.network.di.component

import com.lockwood.core.di.provider.ApplicationProvider
import com.lockwood.core.network.di.module.NetworkApiModule
import com.lockwood.core.network.di.module.NetworkModule
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        ApplicationProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        NetworkModule::class,
        NetworkApiModule::class
    ]
)
@Singleton
interface NetworkComponent : NetworkToolsProvider {

    @Component.Builder
    interface Builder {

        fun applicationProvider(applicationProvider: ApplicationProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun build(): NetworkToolsProvider

    }

}