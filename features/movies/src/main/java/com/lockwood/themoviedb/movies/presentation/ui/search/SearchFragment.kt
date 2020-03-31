package com.lockwood.themoviedb.movies.presentation.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.databinding.FragmentSearchBinding
import com.lockwood.themoviedb.movies.di.component.search.DaggerSearchComponent
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val binding: FragmentSearchBinding by viewBinding()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflateViewBinding<FragmentSearchBinding>(container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.liveState, ::renderState)
        addViewListeners()
    }

    private fun addViewListeners() = with(binding) {
        with(searchInputLayout.searchEditText) {
            setOnFocusChangeListener { _, _ -> viewModel.inputStarted() }
            addTextChangedListener { viewModel.movieNameEntered(it.toString()) }
        }
    }

    private fun renderState(state: SearchViewState) = with(binding) {
        searchTitle.isVisible = !state.inputStarted
        searchImageBackground.isVisible = !state.inputStarted
        searchFragment.isVisible = state.inputStarted
        Timber.d("movieName: ${state.movieName}")
    }


    private fun inject() {
        DaggerSearchComponent.builder()
            .build()
            .inject(this)
    }

}