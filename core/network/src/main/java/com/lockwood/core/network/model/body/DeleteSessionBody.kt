package com.lockwood.core.network.model.body

import androidx.annotation.Keep
import com.lockwood.core.network.common.JsonConstants
import com.squareup.moshi.Json

@Keep
data class DeleteSessionBody(
    @Json(name = JsonConstants.SESSION_ID)
    val sessionId: String // 17
)