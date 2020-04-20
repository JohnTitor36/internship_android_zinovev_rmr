package com.lockwood.themoviedb.login.presentation.ui.pin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.cryptographic.Cryptographer
import com.lockwood.core.event.Event
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.login.databinding.FragmentPinBinding
import com.lockwood.themoviedb.login.di.component.DaggerPinComponent
import com.lockwood.themoviedb.login.event.KeyboardClearEvent
import kotlinx.android.synthetic.main.fragment_pin.*
import javax.inject.Inject

@ExperimentalStdlibApi
class PinFragment : BaseFragment() {

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

        keyboard.addPinKeyboardListener(viewModel.pinKeyboardListener)
        indicators.setupWithPinKeyboard(keyboard)
    }

    override fun onOnEvent(event: Event) {
        super.onOnEvent(event)
        when (event) {
            is KeyboardClearEvent -> {
                binding.keyboard.resetEnteredDigits()
            }
        }
    }

    override fun setupViews() {
        setupAppBar()
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