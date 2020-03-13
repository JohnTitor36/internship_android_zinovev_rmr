package com.lockwood.themoviedb.login.presentation

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.extensions.getViewModel
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import javax.inject.Inject


class LoginFragment : BaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    val viewModel: LoginViewModel by lazy { getViewModel(factory) }

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .applicationProvider(appToolsProvider)
            .build()
            .inject(this)
    }

}