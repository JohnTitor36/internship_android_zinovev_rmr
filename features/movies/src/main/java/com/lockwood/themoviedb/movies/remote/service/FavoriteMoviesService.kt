package com.lockwood.themoviedb.movies.remote.service

import com.lockwood.themoviedb.movies.remote.model.body.MarkAsFavoriteBodyModel
import com.lockwood.themoviedb.movies.remote.model.response.SearchMoviesResponseModel
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface FavoriteMoviesService {

    @POST("account/{account_id}/favorite")
    fun markAsFavorite(
        @Path("accountId") accountId: Int,
        @Body markAsFavoriteBody: MarkAsFavoriteBodyModel
    ): Completable

    @GET("account/{account_id}/favorite/movies")
    fun loadFavoriteMovies(
        @Path("accountId") accountId: Int,
        @Query("page") page: Int,
        @Query("language") language: String
    ): Observable<SearchMoviesResponseModel>

}