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
import androidx.recyclerview.widget.LinearLayoutManager
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.extensions.buildSnackbar
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.databinding.FragmentSearchBinding
import com.lockwood.themoviedb.movies.di.component.search.DaggerSearchComponent
import com.lockwood.themoviedb.movies.domain.model.Movie
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesAdapter
import javax.inject.Inject

class SearchFragment : BaseFragment(), MoviesAdapter.MoviesAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val binding: FragmentSearchBinding by viewBinding()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = createView<FragmentSearchBinding>(inflater, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        addViewListeners()

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)
    }

    private fun setupViews() = with(binding) {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.listener = this@SearchFragment
        with(searchRecyclerViewMovies) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moviesAdapter
        }
    }

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    private fun addViewListeners() {
        with(binding.searchInputLayout.searchEditText) {
            setOnFocusChangeListener { _, _ -> viewModel.inputClicked() }
            addTextChangedListener { viewModel.movieNameEntered(it.toString()) }
        }
    }

    private fun renderState(state: SearchViewState) {
        with(binding) {
            searchTitle.isVisible = !state.inputClicked
            searchImageBackground.isVisible = !state.inputClicked
            searchRecyclerViewMovies.isVisible = state.inputStarted
            moviesAdapter.setItems(state.movies)
        }
    }

    private fun inject() {
        DaggerSearchComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

    override fun onMovieClick(item: Movie) {
        viewModel.openMovie(item.id)
    }

}