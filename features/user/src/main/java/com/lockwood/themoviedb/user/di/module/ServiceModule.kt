package com.lockwood.themoviedb.user.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.network.di.qualifier.AuthRetrofit
import com.lockwood.themoviedb.user.remote.service.AccountService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    @FeatureScope
    fun provideAccountService(@AuthRetrofit retrofit: Retrofit.Builder): AccountService {
        return retrofit.build().create(AccountService::class.java)
    }

}