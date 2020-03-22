package com.lockwood.core.preferences.di.component

import com.lockwood.core.di.provider.ApplicationProvider
import com.lockwood.core.preferences.di.module.PreferencesApiModule
import com.lockwood.core.preferences.di.module.PreferencesModule
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        PreferencesModule::class,
        PreferencesApiModule::class
    ]
)
@Singleton
interface PreferencesComponent : PreferencesToolsProvider {

    @Component.Builder
    interface Builder {

        fun applicationProvider(applicationProvider: ApplicationProvider): Builder

        fun build(): PreferencesToolsProvider

    }

}