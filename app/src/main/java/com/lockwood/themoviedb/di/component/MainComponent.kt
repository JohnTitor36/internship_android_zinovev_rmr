package com.lockwood.themoviedb.di.component

import com.lockwood.core.di.component.BaseActivityComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.ui.MainActivity
import dagger.Component

@Component
@FeatureScope
interface MainComponent : BaseActivityComponent<MainActivity> {

    @Component.Builder
    interface Builder {

        fun build(): MainComponent

    }
}