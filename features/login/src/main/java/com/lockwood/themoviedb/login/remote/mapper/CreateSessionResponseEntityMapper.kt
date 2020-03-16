package com.lockwood.themoviedb.login.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.remote.model.response.CreateSessionResponseModel
import javax.inject.Inject

class CreateSessionResponseEntityMapper @Inject constructor() :
    EntityRemoteMapper<CreateSessionResponseModel, CreateSessionResponseEntity> {

    override fun mapFromRemote(type: CreateSessionResponseModel): CreateSessionResponseEntity {
        return CreateSessionResponseEntity(type.success, type.sessionId)
    }

    override fun mapToRemote(type: CreateSessionResponseEntity): CreateSessionResponseModel {
        return CreateSessionResponseModel(type.success, type.sessionId)
    }

}