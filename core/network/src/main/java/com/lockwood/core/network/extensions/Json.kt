package com.lockwood.core.network.extensions

import com.squareup.moshi.JsonReader

inline fun JsonReader.read(
    readName: JsonReader.(String) -> Unit
) {
    beginObject();
    while (hasNext()) {
        readName(nextName())
    }
    endObject()
}