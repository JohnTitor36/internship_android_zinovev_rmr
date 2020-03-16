package com.lockwood.core.cache.di.component

import com.lockwood.core.cache.di.DaggerCacheApplication
import com.lockwood.core.cache.di.module.CacheApiModule
import com.lockwood.core.cache.di.module.ContextModule
import com.lockwood.core.cache.di.module.PreferencesModule
import com.lockwood.core.cache.di.provider.CacheToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ContextModule::class,
        PreferencesModule::class,
        CacheApiModule::class
    ]
)
@Singleton
interface CacheComponent : CacheToolsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerCacheApplication): Builder

        fun build(): CacheToolsProvider

    }

}