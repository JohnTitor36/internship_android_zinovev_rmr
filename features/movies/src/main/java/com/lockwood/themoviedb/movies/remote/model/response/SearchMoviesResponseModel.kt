package com.lockwood.themoviedb.movies.remote.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class SearchMoviesResponseModel(
    @Json(name = "page")
    val page: Int, // 1
    @Json(name = "total_results")
    val totalResults: Int, // 2
    @Json(name = "total_pages")
    val totalPages: Int, // 1
    @Json(name = "results")
    val results: List<Result>
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "popularity")
        val popularity: Double, // 154.651
        @Json(name = "vote_count")
        val voteCount: Int, // 944
        @Json(name = "video")
        val video: Boolean, // false
        @Json(name = "poster_path")
        val posterPath: String, // /AwsewPKXebJvtl4EU5Dx5uBCW6G.jpg
        @Json(name = "id")
        val id: Int, // 448119
        @Json(name = "adult")
        val adult: Boolean, // false
        @Json(name = "backdrop_path")
        val backdropPath: String, // /xcUf6yIheo78btFqihlRLftdR3M.jpg
        @Json(name = "original_language")
        val originalLanguage: String, // en
        @Json(name = "original_title")
        val originalTitle: String, // Dolittle
        @Json(name = "genre_ids")
        val genreIds: List<Int>,
        @Json(name = "title")
        val title: String, // Удивительное путешествие доктора Дулиттла
        @Json(name = "vote_average")
        val voteAverage: Double, // 6.8
        @Json(name = "overview")
        val overview: String, // Семь лет назад доктор Дулиттл, прославленный врач-ветеринар, живущий в викторианской Англии, потерял свою жену. Теперь он ведет затворнический образ жизни, скрывшись за высокими стенами своего поместья. Экзотические животные из его коллекции — его единственная компания. Но когда неизвестная болезнь становится угрозой для жизни юной королевы, доктору Дулиттлу приходится покинуть свое убежище и отправиться в невероятное путешествие к мифическому острову. В поисках лекарства для королевы он вступит в схватку с беспощадными врагами, познакомится с диковинными существами и будет вынужден проявить недюжинное мужество и смекалку.
        @Json(name = "release_date")
        val releaseDate: Date // 2020-01-01
    )
}