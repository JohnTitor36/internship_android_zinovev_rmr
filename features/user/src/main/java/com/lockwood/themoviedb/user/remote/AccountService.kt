package com.lockwood.themoviedb.user.remote

import com.lockwood.themoviedb.user.remote.model.body.DeleteSessionBodyModel
import com.lockwood.themoviedb.user.remote.model.response.AccountResponseModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Query

interface AccountService {

    @GET("account")
    fun getAccountDetails(
        @Query("api_key") apiKey: String,
        @Query("session_id") sessionId: String
    ): Single<AccountResponseModel>

    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    fun deleteSession(
        @Query("api_key") apiKey: String,
        @Body deleteSessionBody: DeleteSessionBodyModel
    ): Completable

}
