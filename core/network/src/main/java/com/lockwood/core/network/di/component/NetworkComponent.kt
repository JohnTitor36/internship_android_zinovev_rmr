package com.lockwood.core.network.di.component

import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.module.ContextModule
import com.lockwood.core.network.di.module.NetworkApiModule
import com.lockwood.core.network.di.module.NetworkModule
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        PreferencesToolsProvider::class
    ],
    modules = [
        ContextModule::class,
        NetworkModule::class,
        NetworkApiModule::class
    ]
)
@Singleton
interface NetworkComponent : NetworkToolsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerNetworkApplication): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun build(): NetworkToolsProvider

    }

}