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
import androidx.recyclerview.widget.GridLayoutManager
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.addOnLastItemListener
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.extensions.buildSnackbar
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.databinding.FragmentSearchBinding
import com.lockwood.themoviedb.movies.di.component.search.DaggerSearchComponent
import com.lockwood.themoviedb.movies.domain.model.Movie
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesAdapter
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_LIST
import javax.inject.Inject

class SearchFragment : BaseFragment(), MoviesAdapter.MoviesAdapterListener {

    companion object {

        private const val LAST_ITEM_REACHED_OFFSET = 5
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var resourceReader: ResourceReader

    private val binding: FragmentSearchBinding by viewBinding()

    private lateinit var moviesAdapter: MoviesAdapter

    private lateinit var gridLayoutManager: GridLayoutManager

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
        moviesAdapter = MoviesAdapter().apply {
            listener = this@SearchFragment
        }
        gridLayoutManager = GridLayoutManager(requireContext(), 1)
        with(searchRecyclerViewMovies) {
            layoutManager = gridLayoutManager
            adapter = moviesAdapter
            addOnLastItemListener(LAST_ITEM_REACHED_OFFSET) {
                viewModel.loadMoreMovies()
            }
        }
        includeSearchLayout.searchChangeViewType.setOnClickListener {
            viewModel.changeMoviesViewType()
        }
    }

    override fun showMessage(message: String) {
        rootView.buildSnackbar(message).show()
    }

    private fun addViewListeners() {
        with(binding.includeSearchLayout.searchEditText) {
            setOnFocusChangeListener { _, _ -> viewModel.inputClicked() }
            addTextChangedListener { viewModel.movieNameEntered(it.toString()) }
        }
    }

    private fun renderState(state: SearchViewState) {
        with(binding) {
            searchTitle.isVisible = !state.inputClicked
            searchImageBackground.isVisible = !state.inputClicked

            with(includeSearchLayout) {
                val changeTypeDrawable = if (state.viewItemType == ITEM_VIEW_TYPE_LIST) {
                    resourceReader.drawable(R.drawable.ic_movies_card)
                } else {
                    resourceReader.drawable(R.drawable.ic_movies_list)
                }
                searchChangeViewTypeImage.setImageDrawable(changeTypeDrawable)

                val clearDrawable = if (state.inputEmpty) {
                    null
                } else {
                    resourceReader.drawable(R.drawable.ic_clear)
                }
                searchInputLayout.endIconDrawable = clearDrawable
            }

            with(searchRecyclerViewMovies) {
                isVisible = state.inputStarted

                val isTypeChanged = state.viewItemType != moviesAdapter.itemViewType
                if (!isTypeChanged) {
                    // обновляем только отображаемые данные
                    moviesAdapter.setItems(state.movies)
                } else {
                    // обновляем адаптер с отображаемыми данными
                    moviesAdapter = MoviesAdapter(state.movies, state.viewItemType).apply {
                        listener = this@SearchFragment
                    }
                    adapter = moviesAdapter
                    // и кол-во столбцов для gridLayoutManager
                    val spanCount = if (state.viewItemType == ITEM_VIEW_TYPE_LIST) {
                        1
                    } else {
                        2
                    }
                    gridLayoutManager.spanCount = spanCount
                }
            }
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