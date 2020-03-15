package com.lockwood.themoviedb.movies.di.component

import com.lockwood.core.di.component.BaseFragmentComponent
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.MovieFragment
import dagger.Component

@Component
@FeatureScope
interface MovieComponent : BaseFragmentComponent<MovieFragment> {

    @Component.Builder
    interface Builder {

        fun build(): MovieComponent

    }

}