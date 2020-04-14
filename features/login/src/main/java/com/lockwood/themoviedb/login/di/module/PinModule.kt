package com.lockwood.themoviedb.login.di.module

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.data.DefaultAuthenticationRepository
import com.lockwood.themoviedb.login.data.repository.AuthenticationRemote
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.presentation.ui.LoginViewModel
import com.lockwood.themoviedb.login.presentation.ui.pin.PinViewModel
import com.lockwood.themoviedb.login.remote.DefaultAuthenticationRemote
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class PinModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(PinViewModel::class)
    abstract fun providePinViewModel(viewModel: PinViewModel): ViewModel

}