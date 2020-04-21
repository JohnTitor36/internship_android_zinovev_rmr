package com.lockwood.themoviedb.movies.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class SearchMoviesResponseDb(
    val page: Int = 1, // 1
    val totalResults: Int = 1, // 2
    val totalPages: Int = 1, // 1
    val results: List<Result>
) {
    @Entity(tableName = "favorite_movies")
    data class Result(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int, // 448119
        @ColumnInfo(name = "popularity")
        val popularity: Double, // 154.651
        @ColumnInfo(name = "vote_count")
        val voteCount: Int, // 944
        @ColumnInfo(name = "video")
        val video: Boolean, // false
        @ColumnInfo(name = "poster")
        val poster: String, // /AwsewPKXebJvtl4EU5Dx5uBCW6G.jpg
        @ColumnInfo(name = "adult")
        val adult: Boolean, // false
        @ColumnInfo(name = "backdrop")
        val backdrop: String, // /xcUf6yIheo78btFqihlRLftdR3M.jpg
        @ColumnInfo(name = "original_language")
        val originalLanguage: String, // en
        @ColumnInfo(name = "original_title")
        val originalTitle: String, // Dolittle
        @ColumnInfo(name = "genre_ids")
        val genreIds: String,
        @ColumnInfo(name = "title")
        val title: String, // Удивительное путешествие доктора Дулиттла
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double, // 6.8
        @ColumnInfo(name = "overview")
        val overview: String, // Семь лет назад доктор Дулиттл, прославленный врач-ветеринар, живущий в викторианской Англии, потерял свою жену. Теперь он ведет затворнический образ жизни, скрывшись за высокими стенами своего поместья. Экзотические животные из его коллекции — его единственная компания. Но когда неизвестная болезнь становится угрозой для жизни юной королевы, доктору Дулиттлу приходится покинуть свое убежище и отправиться в невероятное путешествие к мифическому острову. В поисках лекарства для королевы он вступит в схватку с беспощадными врагами, познакомится с диковинными существами и будет вынужден проявить недюжинное мужество и смекалку.
        @ColumnInfo(name = "release_date")
        val releaseDate: String // 2020-01-01
    )
}