package com.lockwood.core.network.di.module

import android.content.Context
import com.lockwood.core.network.authenticator.UserToLoginAuthenticator
import com.lockwood.core.network.di.DaggerNetworkApplication
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApplicationContext(application: DaggerNetworkApplication): Context {
        return application.getApplicationContext()
    }

    @Provides
    fun provideTokenAuthenticator(context: Context): Authenticator {
        return UserToLoginAuthenticator(context)
    }

}