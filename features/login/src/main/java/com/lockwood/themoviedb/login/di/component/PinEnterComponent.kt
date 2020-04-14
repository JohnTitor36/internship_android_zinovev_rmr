package com.lockwood.themoviedb.login.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.provider.AppToolsProvider
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.preferences.di.provider.PreferencesToolsProvider
import com.lockwood.themoviedb.login.di.module.PinEnterModule
import com.lockwood.themoviedb.login.presentation.ui.pin.enter.PinEnterFragment
import dagger.Component

@Component(
    dependencies = [
        AppToolsProvider::class,
        PreferencesToolsProvider::class
    ],
    modules = [
        PinEnterModule::class
    ]
)
@FeatureScope
interface PinEnterComponent : BaseFragmentComponent<PinEnterFragment> {

    @Component.Builder
    interface Builder {

        fun appToolsProvider(appToolsProvider: AppToolsProvider): Builder

        fun preferencesToolsProvider(preferencesToolsProvider: PreferencesToolsProvider): Builder

        fun build(): PinEnterComponent

    }

}