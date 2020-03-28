package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.lockwood.core.event.Event
import com.lockwood.core.extensions.*
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.di.component.DaggerLoginComponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject
import javax.inject.Provider

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    companion object {

        private const val MAIN_ACTIVITY_CLASS_NAME =
            "com.lockwood.themoviedb.presentation.ui.MainActivity"
    }

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

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    override fun showError(error: String) {
        login_error_text_view.text = error
        login_error_text_view.isVisible = error.isNotEmpty()
    }

    private fun addViewListeners() {
        login_edit_text.addTextChangedListener { viewModel.setLogin(it.toString()) }
        password_edit_text.addTextChangedListener { viewModel.setPassword(it.toString()) }

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
            val isKeyboardOpened =
                heightDifference > requireContext().dimenInPx(R.dimen.keyboard_probably_height)
            viewModel.keyboardOpened.value = isKeyboardOpened
        }
    }

    private fun observeLiveDataChanges() {
        observeCredentialsInputChanges()
        observeCredentialsLengthChanges()
        observeRequestChanges()
        observeConnectivityChanges()
        observeNavigationChanges()
        observeKeyboardAppearanceChanges()
    }

    private fun observeCredentialsInputChanges() = with(viewModel) {
        val validLengthObserver = Observer<String> { checkIsValidCredentialsLength() }

        observe(loginLiveData) {

        }
        loginLiveData.observe( validLengthObserver)
        passwordLiveData.observe(lifecycleOwner, validLengthObserver)
    }

    private fun observeCredentialsLengthChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner

        val loginButtonObserver = Observer<Boolean> { sign_in_button.isEnabled = it }

        isCredentialsLengthValid.observe(lifecycleOwner, loginButtonObserver)
    }

    private fun observeRequestChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner

        val progressBarObserver = Observer<Boolean> {
            val loginProgressBar = requireActivity().findViewById<View>(R.id.login_progress_bar)
            loginProgressBar.isVisible = it
        }

        val errorMessageObserver = Observer<String> { message ->
            if (message != null) {
                showError(message)
            }
        }

        isLoadingLiveData.observe(lifecycleOwner, progressBarObserver)
        errorMessageLiveData.observe(lifecycleOwner, errorMessageObserver)
    }

    private fun observeConnectivityChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner

        val noInternetObserver = Observer<Event<Unit>> {
            val checkNetworkMessage = getString(R.string.title_check_network_connection)
            showMessage(checkNetworkMessage)
        }

        noInternetConnectionEvent.observe(lifecycleOwner, noInternetObserver)
    }

    private fun observeNavigationChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner

        // TODO: Заменить на переход к пин коду
        val openNextActivityObserver = Observer<Event<Unit>> {
            requireContext().launchActivity(MAIN_ACTIVITY_CLASS_NAME) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        openNextActivityEvent.observe(lifecycleOwner, openNextActivityObserver)
    }

    private fun observeKeyboardAppearanceChanges() = with(viewModel) {
        val lifecycleOwner = viewLifecycleOwner

        val keyboardOpenedObserver = Observer<Boolean> { keyboardOpened ->
            login_title_text_view.isVisible = !keyboardOpened
            login_hint_text_view.isVisible = !keyboardOpened
        }

        keyboardOpened.observe(lifecycleOwner, keyboardOpenedObserver)
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