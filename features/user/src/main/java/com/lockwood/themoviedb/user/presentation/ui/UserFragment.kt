package com.lockwood.themoviedb.user.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.user.R
import com.lockwood.themoviedb.user.di.component.DaggerUserComponent
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject

class UserFragment : BaseFragment(R.layout.fragment_user) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UserViewModel by viewModels { viewModelFactory }

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sign_out_button.setOnClickListener { viewModel.logout() }
        // Для проверки токена пробуем получить информацию об аккаунте
        viewModel.fetchAccountDetails()
    }

    private fun inject() {
        DaggerUserComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

}