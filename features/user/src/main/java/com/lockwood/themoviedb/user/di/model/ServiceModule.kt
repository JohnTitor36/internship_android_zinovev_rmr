package com.lockwood.themoviedb.user.di.model

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.user.remote.AccountService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    @FeatureScope
    fun provideAccountService(@AuthRetrofit retrofit: Retrofit): AccountService {
        return retrofit.create(AccountService::class.java)
    }

}