package com.lockwood.themoviedb.user.presentation.ui

import android.content.Context
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.user.R
import com.lockwood.themoviedb.user.di.component.DaggerUserComponent
import javax.inject.Inject
import javax.inject.Provider

class UserFragment : BaseFragment(R.layout.fragment_user) {

    @Inject
    lateinit var viewModelFactory: Provider<UserViewModel>

    lateinit var viewModel: UserViewModel

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    private fun inject() {
        DaggerUserComponent.builder()
            .build()
            .inject(this)
    }

}