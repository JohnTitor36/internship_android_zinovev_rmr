package com.lockwood.themoviedb.login.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.login.di.module.PinModule
import com.lockwood.themoviedb.login.di.module.ServiceModule
import com.lockwood.themoviedb.login.presentation.ui.pin.PinFragment
import dagger.Component

@ExperimentalStdlibApi
@Component(
    dependencies = [
        AppToolsProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        PinModule::class
    ]
)
@FeatureScope
interface PinComponent : BaseFragmentComponent<PinFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun build(): PinComponent

    }

}