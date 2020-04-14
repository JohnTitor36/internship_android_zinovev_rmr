package com.lockwood.themoviedb.login.presentation.ui.pin.enter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.login.databinding.FragmentEnterPinBinding
import com.lockwood.themoviedb.login.di.component.DaggerPinEnterComponent
import com.lockwood.themoviedb.login.presentation.adapter.PinAdapter
import javax.inject.Inject

class PinEnterFragment : BaseFragment(), PinAdapter.PinAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: PinEnterViewModel by viewModels { viewModelFactory }

    private val binding: FragmentEnterPinBinding by viewBinding()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView<FragmentEnterPinBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)
    }

    override fun setupViews() {

    }

    override fun onDigitClick(digit: Int) {

    }

    override fun onClearClick() {

    }

    override fun onExitClick() {

    }

    private fun renderState(state: PinEnterViewState) {

    }

    private fun setupRecyclerViewKeyboard() {
//        moviesAdapter = MoviesAdapter().apply {
//            listener = this@SearchFragment
//        }
//        gridLayoutManager = GridLayoutManager(requireContext(), 1)
//
//        with(binding.searchRecyclerViewMovies) {
//            layoutManager = gridLayoutManager
//            adapter = moviesAdapter
//            addOnLastItemListener(LAST_ITEM_REACHED_OFFSET) {
//                viewModel.loadMoreMovies()
//            }
//        }
    }

    private fun inject() {
        DaggerPinEnterComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .build()
            .inject(this)
    }

}