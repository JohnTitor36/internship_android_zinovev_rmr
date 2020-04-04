package com.lockwood.themoviedb.movies.di.module.search

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.movies.remote.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchServiceModule {

    @Provides
    @FeatureScope
    fun provideAuthenticationService(@AuthRetrofit retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

}