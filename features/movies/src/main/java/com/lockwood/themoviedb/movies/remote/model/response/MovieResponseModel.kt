package com.lockwood.themoviedb.movies.remote.model.response

import com.lockwood.core.data.Language
import com.lockwood.core.network.moshi.qualifier.BackdropUrl
import com.lockwood.core.network.moshi.qualifier.PosterUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class MovieResponseModel(
    @Json(name = "adult")
    val adult: Boolean, // false
    @BackdropUrl
    @Json(name = "backdrop_path")
    val backdrop: String?, // /pcq2CGl0EhwxqXhwb4etSLvvlKQ.jpg
    @Json(name = "budget")
    val budget: Int, // 4000000
    @Json(name = "genres")
    val genreModels: List<GenreModel>,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val id: Int, // 123
    @Json(name = "imdb_id")
    val imdbId: String, // tt0077869
    @Json(name = "original_language")
    val originalLanguage: Language, // en
    @Json(name = "original_title")
    val originalTitle: String, // The Lord of the Rings
    @Json(name = "overview")
    val overview: String, // Сказания Средиземья — это хроника Великой войны за Кольцо, войны, длившейся не одну тысячу лет. Тот, кто владел Кольцом, получал власть над всеми живыми тварями, но был обязан служить злу. Хоббит Фродо призван уничтожить Кольцо. Он отправился за ним в Мордор и на огненную Гору Судьбы вместе с союзниками — эльфами, гномами, людьми и хоббитами.  «Властелин колец» повествует о борьбе добра и зла, сохраняя сказочную атмосферу классики жанра «фэнтези» — трилогии Дж. Р. Р. Толкиена.
    @Json(name = "popularity")
    val popularity: Double, // 13.336
    @PosterUrl
    @Json(name = "poster_path")
    val poster: String?, // /pphPNH41WVYBVidpf7HVkSIBMe4.jpg
    @Json(name = "release_date")
    val releaseDate: Date, // 1978-11-15
    @Json(name = "revenue")
    val revenue: Int, // 30471420
    @Json(name = "runtime")
    val runtime: Int, // 132
    @Json(name = "status")
    val status: String, // Released
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String, // Властелин колец
    @Json(name = "video")
    val video: Boolean, // false
    @Json(name = "vote_average")
    val voteAverage: Double, // 6.5
    @Json(name = "vote_count")
    val voteCount: Int // 417
) {

    @JsonClass(generateAdapter = true)
    data class GenreModel(
        @Json(name = "id")
        val id: Int, // 12
        @Json(name = "name")
        val name: String // приключения
    )

}