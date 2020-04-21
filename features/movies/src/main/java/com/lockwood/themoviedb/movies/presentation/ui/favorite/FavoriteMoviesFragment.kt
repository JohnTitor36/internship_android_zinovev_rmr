package com.lockwood.themoviedb.movies.presentation.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.addOnLastItemListener
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.extensions.beginDelayedTransition
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.themoviedb.movies.databinding.FragmentFavoriteMoviesBinding
import com.lockwood.themoviedb.movies.di.component.favorite.DaggerFavoriteMoviesComponent
import com.lockwood.themoviedb.movies.domain.model.MovieItem
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesAdapter
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_LIST
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment(), MoviesAdapter.MoviesAdapterListener {

    companion object {

        private const val LAST_ITEM_REACHED_OFFSET = 5
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FavoriteMoviesViewModel by viewModels { viewModelFactory }

    private val binding: FragmentFavoriteMoviesBinding by viewBinding()

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
        return createView<FragmentFavoriteMoviesBinding>(inflater, container)
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
        binding.favoriteTitle.beginDelayedTransition()
        setupRecyclerViewMovies()
    }

    private fun setupRecyclerViewMovies() {
        moviesAdapter = MoviesAdapter().apply {
            listener = this@FavoriteMoviesFragment
        }
        gridLayoutManager = GridLayoutManager(requireContext(), 1)

        with(binding.searchRecyclerViewMovies) {
            layoutManager = gridLayoutManager
            adapter = moviesAdapter
            addOnLastItemListener(LAST_ITEM_REACHED_OFFSET) {
                viewModel.loadMoreMovies()
            }
        }
        viewModel.onFirstLoadMovies()
    }

    private fun renderState(state: FavoriteMoviesViewState) {
        renderMovies(state.movies, state.itemViewType)
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

        with(binding) {
            val isMoviesNullOrEmpty = movies.isNullOrEmpty()
            searchRecyclerViewMovies.isVisible = !isMoviesNullOrEmpty
            favoriteTitleEmpty.isVisible = isMoviesNullOrEmpty
            favoriteTitleSearch.isVisible = isMoviesNullOrEmpty
            favoriteImageBackground.isVisible = isMoviesNullOrEmpty
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
            listener = this@FavoriteMoviesFragment
        }
        binding.searchRecyclerViewMovies.adapter = moviesAdapter

        // обновляем кол-во столбцов в зависимости от типа
        val spanCount = if (itemViewType == ITEM_VIEW_TYPE_LIST) {
            1
        } else {
            2
        }
        gridLayoutManager.spanCount = spanCount
    }

    private fun inject() {
        DaggerFavoriteMoviesComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }


}