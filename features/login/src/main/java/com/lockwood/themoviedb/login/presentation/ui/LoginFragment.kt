package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.afterMeasured
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.extensions.dimenPx
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.databinding.FragmentLoginBinding
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private val binding: FragmentLoginBinding by viewBinding()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView<FragmentLoginBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkEnvironmentSecurity()

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)
    }

    override fun setupViews() {
        addViewListeners()
    }

    private fun renderState(state: LoginViewState) {
        renderLoading(state.loading)
        renderTitleAboveCredentials(state.keyboardOpened)
        renderSigninButton(state.validCredentials)
        renderErrorMessage(state.errorMessage)
    }

    private fun renderLoading(loading: Boolean) {
        val loginProgressBar = requireActivity().findViewById<View>(R.id.login_progress_bar)
        loginProgressBar.isVisible = loading
    }

    private fun renderTitleAboveCredentials(keyboardOpened: Boolean) {
        with(binding) {
            loginTitleTextView.isVisible = !keyboardOpened
            loginHintTextView.isVisible = !keyboardOpened
        }
    }

    private fun renderSigninButton(validCredentials: Boolean) {
        binding.signInButton.isEnabled = validCredentials
    }

    private fun renderErrorMessage(errorMessage: String) {
        binding.loginErrorTextView.text = errorMessage
    }

    private fun addViewListeners() {
        with(binding) {
            loginEditText.addTextChangedListener {
                viewModel.onCredentialsChanged(it.toString(), passwordEditText.text.toString())
            }
            passwordEditText.addTextChangedListener {
                viewModel.onCredentialsChanged(loginEditText.text.toString(), it.toString())
            }

            signInButton.setOnClickListener {
                hideKeyboard()
                viewModel.onEnterButtonClick()
            }
        }

        checkKeyboardVisibility()
    }

    private fun checkKeyboardVisibility() {
        val window = requireActivity().window
        val rootActivityView = window.decorView.findViewById<View>(android.R.id.content)
        rootActivityView.afterMeasured {
            val heightDifference = rootActivityView.rootView.height - rootActivityView.height
            val keyboardProbablyHeight = requireContext().dimenPx(R.dimen.keyboard_probably_height)
            val isKeyboardOpened = heightDifference > keyboardProbablyHeight
            viewModel.onKeyboardOpened(isKeyboardOpened)
        }
    }

    private fun inject() {
        DaggerLoginComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

}