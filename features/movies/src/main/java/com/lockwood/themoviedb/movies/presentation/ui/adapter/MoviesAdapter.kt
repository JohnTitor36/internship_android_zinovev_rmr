package com.lockwood.themoviedb.movies.presentation.ui.adapter

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.lockwood.core.extensions.toYear
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.ui.BaseAdapter
import com.lockwood.core.ui.BaseViewHolder
import com.lockwood.core.viewbinding.inflateViewBinding
import com.lockwood.glide.extensions.drawableRequest
import com.lockwood.glide.extensions.load
import com.lockwood.themoviedb.movies.R
import com.lockwood.themoviedb.movies.databinding.ItemMovieEmtpyBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieGridBinding
import com.lockwood.themoviedb.movies.databinding.ItemMovieListBinding
import com.lockwood.themoviedb.movies.domain.model.Movie
import com.lockwood.themoviedb.movies.presentation.ui.adapter.MoviesItemViewType.ITEM_VIEW_TYPE_LIST
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class MoviesAdapter(
    data: List<Movie> = emptyList(),
    val itemViewType: Int = ITEM_VIEW_TYPE_LIST
) :
    BaseAdapter<Movie>(data) {

    interface MoviesAdapterListener {

        fun onMovieClick(item: Movie)

    }

    lateinit var listener: MoviesAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_CONTENT -> {
                return if (itemViewType == ITEM_VIEW_TYPE_LIST) {
                    val binding =
                        parent.inflateViewBinding<ItemMovieListBinding>(attachToRoot = false)
                    MoviesListViewHolder(binding)
                } else {
                    val binding =
                        parent.inflateViewBinding<ItemMovieGridBinding>(attachToRoot = false)
                    MoviesGridViewHolder(binding)
                }
            }
            else -> {
                val binding =
                    parent.inflateViewBinding<ItemMovieEmtpyBinding>(attachToRoot = false)
                EmptyMoviesViewHolder(binding)
            }
        }
    }

    abstract inner class MoviesViewHolder<T : ViewBinding>(private val itemBinding: T) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) {
            val movie = data[position]

            val context = itemBinding.root.context
            val resourceReader = ResourceReader(context)
            val placeholder = resourceReader.drawable(R.drawable.ic_poster_placeholder)
            val roundedCornersTransformation = RoundedCornersTransformation(
                resourceReader.dimenInPx(R.dimen.item_movies_corner_radius),
                0,
                RoundedCornersTransformation.CornerType.ALL
            )
            val avatarRequest = context.drawableRequest(
                resourceReader = resourceReader,
                placeholder = placeholder,
                fallback = placeholder,
                error = placeholder
            ).apply(RequestOptions.bitmapTransform(roundedCornersTransformation))

            bindMovie(itemBinding, movie, avatarRequest)
        }

        abstract fun bindMovie(
            itemBinding: T,
            movie: Movie,
            avatarRequest: RequestBuilder<Drawable>
        )
    }

    inner class MoviesListViewHolder(itemBinding: ItemMovieListBinding) :
        MoviesViewHolder<ItemMovieListBinding>(itemBinding) {

        override fun bindMovie(
            itemBinding: ItemMovieListBinding,
            movie: Movie,
            avatarRequest: RequestBuilder<Drawable>
        ) = with(itemBinding) {
            val resourceReader = ResourceReader(itemBinding.root.context)
            itemMovieImage.load(movie.poster, avatarRequest).waitForLayout()

            itemMovieTitle.text = movie.title

            val originalTitle = resourceReader.string(R.string.title_movie_with_year)
            val movieYear = movie.releaseDate.toYear()
            val movieOriginalTitle = movie.originalTitle
            itemMovieOriginalTitle.text = originalTitle.format(movieOriginalTitle, movieYear)

            val ratingColor = movie.voteAverage.toRatingColorRes()
            itemMovieVoteAverage.text = movie.voteAverage.toString()

            itemMovieVoteAverage.setTextColor(resourceReader.color(ratingColor))

            itemMoviePopularity.text = movie.popularity.toString()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class MoviesGridViewHolder(itemBinding: ItemMovieGridBinding) :
        MoviesViewHolder<ItemMovieGridBinding>(itemBinding) {

        override fun bindMovie(
            itemBinding: ItemMovieGridBinding,
            movie: Movie,
            avatarRequest: RequestBuilder<Drawable>
        ) = with(itemBinding) {
            val resourceReader = ResourceReader(itemBinding.root.context)
            itemMovieImage.load(movie.poster, avatarRequest).waitForLayout()

            itemMovieTitle.text = movie.title

            val originalTitle = resourceReader.string(R.string.title_movie_with_year)
            val movieYear = movie.releaseDate.toYear()
            val movieOriginalTitle = movie.originalTitle
            itemMovieOriginalTitle.text = originalTitle.format(movieOriginalTitle, movieYear)

            val ratingColor = movie.voteAverage.toRatingColorRes()
            itemMovieVoteAverage.text = movie.voteAverage.toString()

            itemMovieVoteAverage.setTextColor(resourceReader.color(ratingColor))

            itemMoviePopularity.text = movie.popularity.toString()

            root.setOnClickListener { listener.onMovieClick(movie) }
        }
    }

    inner class EmptyMoviesViewHolder(itemBinding: ItemMovieEmtpyBinding) :
        BaseViewHolder(itemBinding.root) {

        override fun onBind(position: Int) = Unit
    }

    private fun Double.toRatingColorRes(): Int {
        return when (this.toInt()) {
            in 10 downTo 8 -> R.color.movie_rating_good
            in 9 downTo 7 -> R.color.movie_rating_normal
            in 6 downTo 4 -> R.color.movie_rating_not_so_good
            else -> R.color.movie_rating_bad
        }
    }

}