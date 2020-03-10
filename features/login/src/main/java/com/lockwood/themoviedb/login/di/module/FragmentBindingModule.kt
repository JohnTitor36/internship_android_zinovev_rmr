package com.lockwood.themoviedb.login.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lockwood.core.di.factory.InjectingFragmentFactory
import com.lockwood.core.di.scope.FragmentKey
import com.lockwood.themoviedb.login.presentation.LoginFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class)
    abstract fun bindLoginFragment(loginFragment: LoginFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: InjectingFragmentFactory): FragmentFactory

}