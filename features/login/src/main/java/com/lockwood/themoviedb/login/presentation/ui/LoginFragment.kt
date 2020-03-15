package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.lockwood.core.extensions.applicationProvider
import com.lockwood.core.extensions.newIntent
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: Provider<LoginViewModel>

    private lateinit var viewModel: LoginViewModel

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        viewModel = viewModelFactory.get()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveDataChanges()
        addViewListeners()
    }

    private fun addViewListeners() {
        login_edit_text.addTextChangedListener { viewModel.setLogin(it.toString()) }
        password_edit_text.addTextChangedListener { viewModel.setPassword(it.toString()) }
        sign_in_button.setOnClickListener { viewModel.login() }
    }

    private fun observeLiveDataChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner
        val validLengthObserver = Observer<String> { checkIsValidCredentialsLength() }
        loginLiveData.observe(lifecycleOwner, validLengthObserver)
        passwordLiveData.observe(lifecycleOwner, validLengthObserver)
        isCredentialsLengthValid.observe(lifecycleOwner, Observer { isValidLength ->
            sign_in_button.isEnabled = isValidLength
        })
        errorMessageLiveData.observe(lifecycleOwner, Observer { message ->
            login_error_text_view.text = message
            login_error_text_view.isVisible = !message.isNullOrEmpty()
        })
        // TODO: Заменить на пин код
        openNextActivityEvent.observe(lifecycleOwner, Observer {
            val intent = newIntent(requireContext(), MAIN_ACTIVITY_CLASS_NAME)
            startActivity(intent)
        })
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .applicationProvider(applicationProvider)
            .build()
            .inject(this)
    }

    companion object {

        private const val MAIN_ACTIVITY_CLASS_NAME = ".presentation.ui.MainActivity"
    }

}