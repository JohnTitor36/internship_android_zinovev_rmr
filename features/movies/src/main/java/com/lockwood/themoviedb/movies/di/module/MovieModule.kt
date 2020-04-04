package com.lockwood.themoviedb.movies.di.module

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MovieModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun provideMovieViewModel(viewModel: MovieViewModel): ViewModel

}