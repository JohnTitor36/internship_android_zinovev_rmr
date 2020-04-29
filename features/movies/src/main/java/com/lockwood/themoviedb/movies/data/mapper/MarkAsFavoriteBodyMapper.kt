package com.lockwood.themoviedb.movies.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import com.lockwood.themoviedb.movies.domain.model.MarkAsFavoriteBody
import javax.inject.Inject

class MarkAsFavoriteBodyMapper @Inject constructor() :
    Mapper<MarkAsFavoriteBodyEntity, MarkAsFavoriteBody> {

    override fun mapFromEntity(type: MarkAsFavoriteBodyEntity): MarkAsFavoriteBody {
        with(type) {
            return MarkAsFavoriteBody(
                mediaType = mediaType,
                mediaId = mediaId,
                favorite = favorite
            )
        }
    }

    override fun mapToEntity(type: MarkAsFavoriteBody): MarkAsFavoriteBodyEntity {
        with(type) {
            return MarkAsFavoriteBodyEntity(
                mediaType = mediaType,
                mediaId = mediaId,
                favorite = favorite
            )
        }
    }

}