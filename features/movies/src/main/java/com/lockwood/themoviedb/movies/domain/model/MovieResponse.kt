package com.lockwood.themoviedb.movies.domain.model

import com.lockwood.core.data.Language

typealias Movie = MovieResponse

data class MovieResponse(
    val adult: Boolean, // false
    val backdropPath: String, // /pcq2CGl0EhwxqXhwb4etSLvvlKQ.jpg
    val budget: Int, // 4000000
    val genreModels: List<Genre>,
    val homepage: String,
    val id: Int, // 123
    val imdbId: String, // tt0077869
    val originalLanguage: Language, // en
    val originalTitle: String, // The Lord of the Rings
    val overview: String, // Сказания Средиземья — это хроника Великой войны за Кольцо, войны, длившейся не одну тысячу лет. Тот, кто владел Кольцом, получал власть над всеми живыми тварями, но был обязан служить злу. Хоббит Фродо призван уничтожить Кольцо. Он отправился за ним в Мордор и на огненную Гору Судьбы вместе с союзниками — эльфами, гномами, людьми и хоббитами.  «Властелин колец» повествует о борьбе добра и зла, сохраняя сказочную атмосферу классики жанра «фэнтези» — трилогии Дж. Р. Р. Толкиена.
    val popularity: Double, // 13.336
    val posterPath: String, // /pphPNH41WVYBVidpf7HVkSIBMe4.jpg
    val releaseDate: String, // 1978-11-15
    val revenue: Int, // 30471420
    val runtime: Int, // 132
    val status: String, // Released
    val tagline: String,
    val title: String, // Властелин колец
    val video: Boolean, // false
    val voteAverage: Double, // 6.5
    val voteCount: Int // 417
) {

    data class Genre(
        val id: Int, // 12
        val name: String // приключения
    )

}