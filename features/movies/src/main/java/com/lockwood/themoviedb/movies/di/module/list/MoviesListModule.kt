package com.lockwood.themoviedb.movies.di.module.list

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.list.MoviesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MoviesListModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun provideMoviesListViewModel(viewModel: MoviesListViewModel): ViewModel

}