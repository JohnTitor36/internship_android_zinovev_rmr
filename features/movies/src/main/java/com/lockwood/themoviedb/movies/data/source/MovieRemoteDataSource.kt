package com.lockwood.themoviedb.movies.data.source

import com.lockwood.themoviedb.movies.data.model.MovieResponseEntity
import com.lockwood.themoviedb.movies.data.repository.MovieDataStore
import com.lockwood.themoviedb.movies.data.repository.MovieRemote
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieRemote: MovieRemote
) : MovieDataStore {

    override fun loadMovie(movieId: Int, language: String): Single<MovieResponseEntity> {
        return movieRemote.loadMovie(movieId, language)
    }

}