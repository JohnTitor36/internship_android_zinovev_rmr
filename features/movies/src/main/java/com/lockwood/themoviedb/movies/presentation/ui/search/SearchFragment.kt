package com.lockwood.themoviedb.movies.presentation.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.*
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
import com.lockwood.themoviedb.movies.domain.model.MovieItem
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesAdapter
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType
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
    ): View {
        return createView<FragmentSearchBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)
    }

    override fun onMovieClick(item: MovieItem) {
        viewModel.openMovie(item.id)
    }

    override fun setupViews() {
        setupRecyclerViewMovies()
        setupIncludeSearchLayout()
        addViewListeners()
    }

    private fun setupRecyclerViewMovies() {
        moviesAdapter = MoviesAdapter().apply {
            listener = this@SearchFragment
        }
        gridLayoutManager = GridLayoutManager(requireContext(), 1)

        with(binding.searchRecyclerViewMovies) {
            layoutManager = gridLayoutManager
            adapter = moviesAdapter
            addOnLastItemListener(LAST_ITEM_REACHED_OFFSET) {
                viewModel.loadMoreMovies()
            }
        }
    }

    private fun setupIncludeSearchLayout() {
        with(binding.includeSearchLayout) {
            searchChangeViewType.setOnClickListener {
                viewModel.onChangeMoviesViewType()
            }

            searchEditText.setOnEndDrawableClickListener {
                (this as EditText).text.clear()
            }
        }
    }

    private fun addViewListeners() {
        with(binding.includeSearchLayout.searchEditText) {
            setOnFocusChangeListener { _, _ -> viewModel.onInputClicked() }
            addTextChangedListener { viewModel.onMovieNameEntered(it.toString()) }
        }
    }

    private fun renderState(state: SearchViewState) {
        renderInputClicked(state.inputClicked)
        renderInputStarted(state.inputStarted)
        renderInputEmpty(state.inputEmpty)
        renderItemViewType(state.itemViewType)
        renderMovies(state.movies, state.itemViewType)
    }

    private fun renderInputClicked(inputClicked: Boolean) {
        with(binding) {
            searchTitle.isVisible = !inputClicked
            searchImageBackground.isVisible = !inputClicked
        }
    }

    private fun renderInputStarted(inputStarted: Boolean) {
        with(binding) {
            if (!inputStarted) {
                searchTitle.beginDelayedTransition()
            } else {
                searchTitle.endDelayedTransition()
            }
            searchRecyclerViewMovies.isVisible = inputStarted
        }
    }

    private fun renderInputEmpty(inputEmpty: Boolean) {
        val clearDrawable = if (inputEmpty) {
            null
        } else {
            resourceReader.drawable(R.drawable.ic_clear)
        }
        binding.includeSearchLayout.searchEditText.updateCompoundDrawables(end = clearDrawable)
    }

    private fun renderItemViewType(itemViewType: Int) {
        with(binding.includeSearchLayout) {
            val changeTypeDrawable =
                if (itemViewType == MoviesItemViewType.ITEM_VIEW_TYPE_LIST) {
                    resourceReader.drawable(R.drawable.ic_movies_card)
                } else {
                    resourceReader.drawable(R.drawable.ic_movies_list)
                }
            searchChangeViewTypeImage.setImageDrawable(changeTypeDrawable)
        }
    }

    private fun renderMovies(movies: List<MovieItem>, itemViewType: Int) {
        val isTypeChanged = itemViewType != moviesAdapter.itemViewType
        if (!isTypeChanged) {
            // обновляем только отображаемые данные
            setMovies(movies)
        } else {
            // обновляем адаптер с отображаемыми данными
            updateAdapterWithMovies(movies, itemViewType)
        }
    }

    private fun setMovies(movies: List<MovieItem>) {
        moviesAdapter.setItems(movies)
    }

    private fun updateAdapterWithMovies(
        movies: List<MovieItem>,
        itemViewType: Int
    ) {
        moviesAdapter = MoviesAdapter(movies, itemViewType).apply {
            listener = this@SearchFragment
        }
        binding.searchRecyclerViewMovies.adapter = moviesAdapter

        // обновляем кол-во столбцов в зависимости от типа
        val spanCount = if (itemViewType == MoviesItemViewType.ITEM_VIEW_TYPE_LIST) {
            1
        } else {
            2
        }
        gridLayoutManager.spanCount = spanCount
    }

    private fun inject() {
        DaggerSearchComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }


}