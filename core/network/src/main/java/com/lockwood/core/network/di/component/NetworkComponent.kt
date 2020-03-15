package com.lockwood.core.network.di.component

import com.lockwood.core.network.di.DaggerNetworkApplication
import com.lockwood.core.network.di.module.ApiModule
import com.lockwood.core.network.di.module.NetworkModule
import com.lockwood.core.network.di.provider.NetworkToolsProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, NetworkModule::class])
@Singleton
interface NetworkComponent : NetworkToolsProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: DaggerNetworkApplication): Builder

        fun build(): NetworkToolsProvider

    }

}