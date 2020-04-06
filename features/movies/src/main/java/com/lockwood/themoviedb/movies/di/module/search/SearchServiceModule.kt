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
    fun provideAuthenticationService(@AuthRetrofit retrofit: Retrofit.Builder): MoviesService {
        return retrofit.build().create(MoviesService::class.java)
    }

}