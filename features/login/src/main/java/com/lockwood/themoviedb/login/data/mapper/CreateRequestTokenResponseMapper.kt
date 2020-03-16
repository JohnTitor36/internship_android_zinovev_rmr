package com.lockwood.themoviedb.login.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import javax.inject.Inject

class CreateRequestTokenResponseMapper @Inject constructor() :
    Mapper<CreateRequestTokenResponseEntity, CreateRequestTokenResponse> {

    override fun mapFromEntity(type: CreateRequestTokenResponseEntity): CreateRequestTokenResponse {
        return CreateRequestTokenResponse(type.success, type.expiresAt, type.requestToken)
    }

    override fun mapToEntity(type: CreateRequestTokenResponse): CreateRequestTokenResponseEntity {
        return CreateRequestTokenResponseEntity(type.success, type.expiresAt, type.requestToken)
    }

}