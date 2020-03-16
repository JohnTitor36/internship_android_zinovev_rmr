package com.lockwood.themoviedb.login.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.remote.model.response.CreateRequestTokenResponseModel
import javax.inject.Inject

class CreateRequestTokenResponseEntityMapper @Inject constructor() :
    EntityRemoteMapper<CreateRequestTokenResponseModel, CreateRequestTokenResponseEntity> {

    override fun mapFromRemote(type: CreateRequestTokenResponseModel): CreateRequestTokenResponseEntity {
        return CreateRequestTokenResponseEntity(type.success, type.expiresAt, type.requestToken)
    }

    override fun mapToRemote(type: CreateRequestTokenResponseEntity): CreateRequestTokenResponseModel {
        return CreateRequestTokenResponseModel(type.success, type.expiresAt, type.requestToken)
    }

}