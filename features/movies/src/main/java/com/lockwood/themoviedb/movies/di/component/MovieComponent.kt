package com.lockwood.themoviedb.movies.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.di.module.MovieModule
import com.lockwood.themoviedb.movies.presentation.ui.MovieFragment
import dagger.Component

@Component(modules = [MovieModule::class])
@FeatureScope
interface MovieComponent : BaseFragmentComponent<MovieFragment> {

    @Component.Builder
    interface Builder {

        fun build(): MovieComponent

    }

}