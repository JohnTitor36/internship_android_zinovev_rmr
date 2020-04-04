package com.lockwood.themoviedb.user.di.module

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.user.data.DefaultAccountRepository
import com.lockwood.themoviedb.user.data.repository.AccountRemote
import com.lockwood.themoviedb.user.domain.repository.AccountRepository
import com.lockwood.themoviedb.user.presentation.ui.UserViewModel
import com.lockwood.themoviedb.user.remote.DefaultAccountRemote
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class UserModule {

    @Binds
    @FeatureScope
    abstract fun provideAccountRepository(account: DefaultAccountRepository): AccountRepository

    @Binds
    @FeatureScope
    abstract fun provideAccountRemote(account: DefaultAccountRemote): AccountRemote

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun provideUserViewModel(viewModel: UserViewModel): ViewModel

}