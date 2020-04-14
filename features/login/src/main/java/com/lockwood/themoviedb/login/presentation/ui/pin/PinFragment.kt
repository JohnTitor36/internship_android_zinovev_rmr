package com.lockwood.themoviedb.login.presentation.ui.pin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.login.databinding.FragmentPinBinding
import com.lockwood.themoviedb.login.di.component.DaggerPinComponent
import com.lockwood.themoviedb.login.presentation.adapter.PinAdapter
import javax.inject.Inject

class PinFragment : BaseFragment(), PinAdapter.PinAdapterListener {

    companion object {

        private const val PIN_KEYBOARD_SPANS_COUNT = 3
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PinViewModel by viewModels { viewModelFactory }

    private val binding: FragmentPinBinding by viewBinding()

    private lateinit var pinAdapter: PinAdapter

    private lateinit var gridLayoutManager: GridLayoutManager

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
    }

    override fun setupViews() {
        setupAppBar()
        setupRecyclerViewKeyboard()
    }

    override fun onDigitClick(digit: Int) {

    }

    override fun onClearClick() {

    }

    override fun onExitClick() {
    }


    private fun renderState(state: PinViewState) {

    }

    private fun setupAppBar() {
        with(binding.movieAppbar) {
            pinBackButton.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun setupRecyclerViewKeyboard() {
        pinAdapter = PinAdapter().apply {
            listener = this@PinFragment
        }
        gridLayoutManager = GridLayoutManager(requireContext(), PIN_KEYBOARD_SPANS_COUNT)

        with(binding.recyclerView) {
            layoutManager = gridLayoutManager
            adapter = pinAdapter
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