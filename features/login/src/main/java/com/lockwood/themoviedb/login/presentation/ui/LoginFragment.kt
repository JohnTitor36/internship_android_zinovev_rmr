package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.event.LaunchActivityEvent
import com.lockwood.core.event.Event
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.*
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override val hasOptionMenu: Boolean = false

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveDataChanges()
        addViewListeners()
    }

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    override fun showError(error: String) = with(login_error_text_view) {
        text = error
        isVisible = error.isNotEmpty()
    }

    override fun onOnEvent(event: Event) {
        super.onOnEvent(event)
        when (event) {
            is LaunchActivityEvent -> requireContext().launchActivity(event.className) {
                flags = event.flags
            }
        }
    }

    private fun addViewListeners() {
        login_edit_text.addTextChangedListener { viewModel.login = it.toString() }
        password_edit_text.addTextChangedListener { viewModel.password = it.toString() }

        sign_in_button.setOnClickListener {
            hideKeyboard()
            viewModel.login()
        }

        checkKeyboardVisibility()
    }

    private fun checkKeyboardVisibility() {
        val rootActivityView =
            requireActivity().window.decorView.findViewById<View>(android.R.id.content)
        rootActivityView.afterMeasured {
            val heightDifference = rootActivityView.rootView.height - rootActivityView.height
            val keyboardProbablyHeight = requireContext().dimenPx(R.dimen.keyboard_probably_height)
            val isKeyboardOpened = heightDifference > keyboardProbablyHeight
            viewModel.keyboardOpened = isKeyboardOpened
        }
    }

    private fun observeLiveDataChanges() {
        observeCredentialsInputChanges()
        observeCredentialsLengthChanges()
        observeRequestChanges()
        observeEvents()
        observeKeyboardAppearanceChanges()
    }

    private fun observeCredentialsInputChanges() {
        val validLengthObserver = Observer<String> { viewModel.checkIsValidCredentialsLength() }
        observe(viewModel.loginLiveData, validLengthObserver)
        observe(viewModel.passwordLiveData, validLengthObserver)
    }

    private fun observeCredentialsLengthChanges() {
        observe(viewModel.isCredentialsLengthValid) { sign_in_button.isEnabled = it }
    }

    private fun observeRequestChanges() {
        observe(viewModel.isLoadingLiveData) { isLoading ->
            val loginProgressBar = requireActivity().findViewById<View>(R.id.login_progress_bar)
            loginProgressBar.isVisible = isLoading
        }
        observe(viewModel.errorMessageLiveData) { message ->
            if (message != null) {
                showError(message)
            }
        }
    }

    private fun observeEvents() {
        observe(viewModel.eventsQueue, ::onOnEvent)
    }

    private fun observeKeyboardAppearanceChanges() {
        observe(viewModel.keyboardOpenedLiveData) { keyboardOpened ->
            login_title_text_view.isVisible = !keyboardOpened
            login_hint_text_view.isVisible = !keyboardOpened
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