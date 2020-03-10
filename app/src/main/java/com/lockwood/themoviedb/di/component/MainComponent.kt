package com.lockwood.themoviedb.di.component

import com.lockwood.core.di.component.BaseActivityComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.di.model.MainActivityModule
import com.lockwood.themoviedb.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MainActivityModule::class])
@FeatureScope
interface MainComponent : BaseActivityComponent<MainActivity> {

    @Component.Builder
    interface Builder {

        fun build(): MainComponent

        @BindsInstance
        fun activity(activity: MainActivity): Builder

        fun mainActivityModule(module: MainActivityModule): Builder

    }
}