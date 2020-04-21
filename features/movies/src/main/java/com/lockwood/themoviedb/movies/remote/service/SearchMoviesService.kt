package com.lockwood.themoviedb.movies.remote.service

import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
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