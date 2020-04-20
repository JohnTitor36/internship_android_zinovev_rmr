package com.lockwood.themoviedb.movies.remote

import com.lockwood.themoviedb.movies.remote.model.body.MarkAsFavoriteBody
import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface SearchMoviesService {

    @GET("search/movie")
    fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<SearchMoviesResponseModel>

    @GET("genre/movie/list")
    fun searchGenres(
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<SearchMoviesResponseModel>

}
