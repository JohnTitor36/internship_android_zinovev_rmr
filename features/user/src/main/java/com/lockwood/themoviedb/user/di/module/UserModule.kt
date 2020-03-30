package com.lockwood.themoviedb.user.di.module

import androidx.lifecycle.ViewModel
import com.lockwood.core.di.key.ViewModelKey
import com.lockwood.core.di.module.ViewModelModule
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.user.presentation.ui.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class UserModule {

    @Binds
    @FeatureScope
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun provideUserViewModel(viewModel: UserViewModel): ViewModel

}