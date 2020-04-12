package com.lockwood.themoviedb.user.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.user.data.model.DeleteSessionBodyEntity
import com.lockwood.themoviedb.user.domain.model.DeleteSessionBody
import javax.inject.Inject

class DeleteSessionBodyMapper @Inject constructor() :
    Mapper<DeleteSessionBodyEntity, DeleteSessionBody> {

    override fun mapFromEntity(type: DeleteSessionBodyEntity): DeleteSessionBody {
        return DeleteSessionBody(type.sessionId)
    }

    override fun mapToEntity(type: DeleteSessionBody): DeleteSessionBodyEntity {
        return DeleteSessionBodyEntity(type.sessionId)
    }

}