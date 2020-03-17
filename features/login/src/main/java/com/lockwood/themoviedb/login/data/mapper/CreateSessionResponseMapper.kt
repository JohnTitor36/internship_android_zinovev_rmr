package com.lockwood.themoviedb.login.data.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import javax.inject.Inject

class CreateSessionResponseMapper @Inject constructor() :
    EntityRemoteMapper<CreateSessionResponseEntity, CreateSessionResponse> {

    override fun mapFromRemote(type: CreateSessionResponseEntity): CreateSessionResponse {
        return CreateSessionResponse(type.success, type.sessionId)
    }

    override fun mapToRemote(type: CreateSessionResponse): CreateSessionResponseEntity {
        return CreateSessionResponseEntity(type.success, type.sessionId)
    }

}