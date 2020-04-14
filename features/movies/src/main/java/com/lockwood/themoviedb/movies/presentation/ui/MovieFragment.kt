package com.lockwood.themoviedb.movies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.request.RequestOptions
import com.lockwood.core.event.observe
import com.lockwood.core.extensions.appToolsProvider
import com.lockwood.core.livedata.observe
import com.lockwood.core.network.extensions.networkToolsProvider
import com.lockwood.core.preferences.extensions.preferencesToolsProvider
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseFragment
import com.lockwood.core.viewbinding.createView
import com.lockwood.core.viewbinding.viewBinding
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.databinding.FragmentMovieBinding
import com.lockwood.themoviedb.movies.di.component.DaggerMovieComponent
import com.lockwood.themoviedb.movies.domain.model.Movie
import com.lockwood.themoviedb.movies.utils.MovieUtils
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import javax.inject.Inject

class MovieFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MovieViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var resourceReader: ResourceReader

    private val binding: FragmentMovieBinding by viewBinding()

    val args: MovieFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createView<FragmentMovieBinding>(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        observe(viewModel.eventsQueue, ::onOnEvent)
        observe(viewModel.liveState, ::renderState)

        val movieId = args.movieIdArg
        viewModel.onLoadMovie(movieId)
    }

    private fun setupView() {
        with(binding.movieAppbar) {
            movieBackButton.setOnClickListener { requireActivity().onBackPressed() }
            movieFavoriteButton.setOnClickListener { viewModel.onFavoriteClicked() }
        }
    }

    private fun renderState(state: MovieViewState) {
        with(binding) {

            movieProgressBar.isVisible = state.loading
            movieContent.isVisible = !state.loading && state.movie != null

            val favoriteDrawable = if (state.favoriteMovie) {
                resourceReader.drawable(R.drawable.ic_favorite)
            } else {
                resourceReader.drawable(R.drawable.ic_favorite_border)
            }
            movieAppbar.movieFavoriteButton.setImageDrawable(favoriteDrawable)
            movieAppbar.movieFavoriteButton.isEnabled = !state.loading

            val movie = state.movie
            if (movie != null) {
                renderMovie(movie)
            }
        }
    }

    private fun renderMovie(movie: Movie) {
        val placeholder = resourceReader.drawable(R.drawable.ic_poster_placeholder)
        val roundedCornersTransformation = RoundedCornersTransformation(
            resourceReader.dimenInPx(R.dimen.item_movies_corner_radius),
            0,
            RoundedCornersTransformation.CornerType.ALL
        )
        val imageRequest = requireContext().drawableRequest(
            resourceReader = resourceReader,
            placeholder = placeholder,
            fallback = placeholder,
            error = placeholder
        ).apply(RequestOptions.bitmapTransform(roundedCornersTransformation))

        val ratingColorRes = MovieUtils.ratingToColorRes(movie.voteAverage)
        val ratingColor = resourceReader.color(ratingColorRes)

        with(binding.movieInfo) {

            movieTitle.text = movie.title
            movieOriginalTitle.text = MovieUtils.buildOriginalTitle(
                resourceReader,
                movie.originalTitle,
                movie.releaseDate
            )

            movieVoteAverage.text = movie.voteAverage.toString()
            movieVoteAverage.setTextColor(ratingColor)

            val genres = movie.genreModels.map { it.name }
            movieGenres.text = MovieUtils.buildGenresCaption(genres)

            movieDuration.text = MovieUtils.buildDurationTitle(
                resourceReader,
                movie.runtime
            )

            moviePopularity.text = movie.popularity.toString()

            movieImage.load(movie.poster, imageRequest)
        }

        binding.movieDescription.text = movie.overview
    }

    private fun inject() {
        DaggerMovieComponent.builder()
            .appToolsProvider(appToolsProvider)
            .preferencesToolsProvider(preferencesToolsProvider)
            .networkToolsProvider(networkToolsProvider)
            .build()
            .inject(this)
    }

}