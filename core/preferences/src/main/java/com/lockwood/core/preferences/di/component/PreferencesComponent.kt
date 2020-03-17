package com.lockwood.core.preferences.di.component

import com.lockwood.core.preferences.di.DaggerPreferencesApplication
import com.lockwood.core.preferences.di.module.ContextModule
import com.lockwood.core.preferences.di.module.PreferencesApiModule
import com.lockwood.core.preferences.di.module.PreferencesModule
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ContextModule::class,
        PreferencesModule::class,
        PreferencesApiModule::class
    ]
)
@Singleton
interface PreferencesComponent : PreferencesToolsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerPreferencesApplication): Builder

        fun build(): PreferencesToolsProvider

    }

}