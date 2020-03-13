package com.lockwood.themoviedb.login.presentation

import android.content.Context
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: Provider<LoginViewModel>

    lateinit var viewModel: LoginViewModel

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .applicationProvider(appToolsProvider)
            .build()
            .inject(this)
    }

}