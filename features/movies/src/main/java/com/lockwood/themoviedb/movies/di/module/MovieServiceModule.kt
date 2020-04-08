package com.lockwood.themoviedb.movies.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.movies.remote.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieServiceModule {

    @Provides
    @FeatureScope
    fun provideMovieService(@AuthRetrofit retrofit: Retrofit.Builder): MovieService {
        return retrofit.build().create(MovieService::class.java)
    }

}