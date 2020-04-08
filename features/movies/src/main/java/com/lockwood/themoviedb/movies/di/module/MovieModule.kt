package com.lockwood.themoviedb.movies.di.module

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.movies.data.DefaultMovieRepository
import com.lockwood.themoviedb.movies.data.repository.MovieRemote
import com.lockwood.themoviedb.movies.domain.repository.MovieRepository
import com.lockwood.themoviedb.movies.presentation.ui.MovieViewModel
import com.lockwood.themoviedb.movies.remote.DefaultMovieRemote
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MovieModule {

    @Binds
    @FeatureScope
    abstract fun provideMovieRepository(movieRepository: DefaultMovieRepository): MovieRepository

    @Binds
    @FeatureScope
    abstract fun provideMovieRemote(MovieRemote: DefaultMovieRemote): MovieRemote

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun provideMovieViewModel(viewModel: MovieViewModel): ViewModel

}