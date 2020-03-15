package com.lockwood.core.network.service

import com.lockwood.core.network.common.JsonConstants
import com.lockwood.core.network.model.body.CreateSessionBody
import com.lockwood.core.network.model.body.ValidateWithLoginBody
import com.lockwood.core.network.model.response.CreateRequestTokenResponse
import com.lockwood.core.network.model.response.CreateSessionResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface AuthenticationService {

    @GET("/authentication/token/new")
    fun createRequestToken(
        @Query(JsonConstants.API_KEY) apiKey: String
    ): Single<CreateRequestTokenResponse>

    @POST("/authentication/token/validate_with_login")
    fun validateTokenWithLogin(
        @Query(JsonConstants.API_KEY) apiKey: String,
        @Body loginBody: ValidateWithLoginBody
    ): Completable

    @POST("/authentication/session/new")
    fun createSession(
        @Query(JsonConstants.API_KEY) apiKey: String,
        @Body sessionBody: CreateSessionBody
    ): Single<CreateSessionResponse>

//    @DELETE("/authentication/session")
//    fun deleteSession(
//        @Query(JsonConstants.API_KEY) apiKey: String,
//        @Body sessionBody: DeleteSessionBody
//    ): Completable

}
