package com.lockwood.core.network.moshi.adapter

import com.lockwood.core.network.common.Language
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class LanguageAdapter {

    companion object {

        private val UNKNOWN_FALLBACK: Language = Language.ENG

        private val LanguageValues = Language.values()
    }

    @ToJson
    fun toJson(language: Language): String {
        return language.value
    }

    @FromJson
    fun fromJson(language: String): Language {
        return LanguageValues.find { it.name == language } ?: UNKNOWN_FALLBACK
    }

}