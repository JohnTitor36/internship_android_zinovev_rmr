package com.lockwood.core.di.component

import com.lockwood.core.di.DaggerApplication
import com.lockwood.core.di.module.CoreModule
import com.lockwood.core.di.module.SecurityModule
import com.lockwood.core.di.provider.AppToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        CoreModule::class,
        SecurityModule::class
    ]
)
@Singleton
interface CoreComponent : AppToolsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerApplication): Builder

        fun build(): AppToolsProvider

    }

}