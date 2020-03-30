package com.lockwood.themoviedb.movies.di.component.list

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.di.module.list.MoviesModule
import com.lockwood.themoviedb.movies.presentation.ui.list.MoviesFragment
import dagger.Component

@Component(modules = [MoviesModule::class])
@FeatureScope
interface MoviesComponent : BaseFragmentComponent<MoviesFragment> {

    @Component.Builder
    interface Builder {

        fun build(): MoviesComponent

    }

}