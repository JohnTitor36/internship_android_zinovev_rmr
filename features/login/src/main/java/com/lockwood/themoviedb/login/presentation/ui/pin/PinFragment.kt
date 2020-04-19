package com.lockwood.themoviedb.login.presentation.ui.pin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.cryptographic.Cryptographer
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.pin.keyboard.listener.PinKeyboardListener
import com.lockwood.themoviedb.login.databinding.FragmentPinBinding
import com.lockwood.themoviedb.login.di.component.DaggerPinComponent
import kotlinx.android.synthetic.main.fragment_pin.*
import timber.log.Timber
import javax.inject.Inject

@ExperimentalStdlibApi
class PinFragment : BaseFragment(), PinKeyboardListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PinViewModel by viewModels { viewModelFactory }

    private val binding: FragmentPinBinding by viewBinding()

    @Inject
    lateinit var cryptographer: Cryptographer

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView<FragmentPinBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)

        keyboard.addPinKeyboardListener(this@PinFragment)
        indicators.setupWithPinKeyboard(keyboard)
    }

    override fun setupViews() {
        setupAppBar()
    }

    override fun onDigitClick(digit: Int) {
    }

    override fun onClearDigitClick() {
    }

    override fun onLastItemEntered(digits: String) {
    }

    override fun onExitClick() {
    }

    override fun onResetEnteredDigits() {
    }

    private fun renderState(state: PinViewState) {

    }

    private fun setupAppBar() {
        with(binding.movieAppbar) {
            pinBackButton.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun inject() {
        DaggerPinComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .build()
            .inject(this)
    }

}