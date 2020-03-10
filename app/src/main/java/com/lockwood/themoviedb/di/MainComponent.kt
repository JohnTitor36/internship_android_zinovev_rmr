package com.lockwood.themoviedb.di

import com.lockwood.core.di.BaseActivityComponent
import com.lockwood.core.di.scope.FeatureScope
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