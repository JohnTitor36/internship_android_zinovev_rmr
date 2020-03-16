package com.lockwood.core.network.di.module

import android.content.Context
import com.lockwood.core.network.authenticator.UserToLoginAuthenticator
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator

@Module
class NetworkModule {

    @Provides
    fun provideTokenAuthenticator(context: Context): Authenticator {
        return UserToLoginAuthenticator(context)
    }

}