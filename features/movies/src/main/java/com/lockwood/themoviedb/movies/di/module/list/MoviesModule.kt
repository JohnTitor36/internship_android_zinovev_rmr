package com.lockwood.themoviedb.movies.di.module.list

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.presentation.ui.list.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MoviesModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun provideMoviesViewModel(viewModel: MoviesViewModel): ViewModel

}