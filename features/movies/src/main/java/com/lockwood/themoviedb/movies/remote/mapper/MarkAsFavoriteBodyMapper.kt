package com.lockwood.themoviedb.movies.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.remote.model.body.MarkAsFavoriteBodyModel
import javax.inject.Inject

class MarkAsFavoriteBodyMapper @Inject constructor() :
    EntityRemoteMapper<MarkAsFavoriteBodyModel, MarkAsFavoriteBodyEntity> {

    override fun mapFromRemote(type: MarkAsFavoriteBodyModel): MarkAsFavoriteBodyEntity {
        with(type) {
            return MarkAsFavoriteBodyEntity(
                mediaType = mediaType,
                mediaId = mediaId,
                favorite = favorite
            )
        }
    }

    override fun mapToRemote(type: MarkAsFavoriteBodyEntity): MarkAsFavoriteBodyModel {
        with(type) {
            return MarkAsFavoriteBodyModel(
                mediaType = mediaType,
                mediaId = mediaId,
                favorite = favorite
            )
        }
    }

}