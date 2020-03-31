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
import com.lockwood.core.event.Event
import com.lockwood.core.event.LaunchActivityEvent
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.*
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.inflateViewBinding
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
    ): View = inflater.inflateViewBinding<FragmentLoginBinding>(container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)
        observe(viewModel.loading, ::renderLoading)
        addViewListeners()
    }

    override fun onOnEvent(event: Event) {
        super.onOnEvent(event)
        when (event) {
            is LaunchActivityEvent -> requireContext().launchActivity(event.className) {
                flags = event.flags
            }
        }
    }

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    override fun showError(error: String) = with(binding.loginErrorTextView) {
        text = error
        isVisible = error.isNotEmpty()
    }

    private fun renderState(state: LoginViewState) = with(binding) {
        signInButton.isEnabled = state.validCredentials

        loginTitleTextView.isVisible = !state.keyboardOpened
        loginHintTextView.isVisible = !state.keyboardOpened
    }

    private fun renderLoading(loading: Boolean) {
        val loginProgressBar = requireActivity().findViewById<View>(R.id.login_progress_bar)
        loginProgressBar.isVisible = loading
    }

    private fun addViewListeners() = with(binding) {
        loginEditText.addTextChangedListener { viewModel.onLoginChanged(it.toString()) }
        passwordEditText.addTextChangedListener { viewModel.onPasswordChanged(it.toString()) }

        signInButton.setOnClickListener {
            hideKeyboard()
            viewModel.login()
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