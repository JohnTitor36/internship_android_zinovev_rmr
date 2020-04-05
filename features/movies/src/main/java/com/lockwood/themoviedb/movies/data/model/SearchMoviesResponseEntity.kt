package com.lockwood.themoviedb.movies.data.model

import com.lockwood.core.network.common.Language
import java.util.*

data class SearchMoviesResponseEntity(
    val page: Int, // 1
    val totalResults: Int, // 2
    val totalPages: Int, // 1
    val results: List<Result>
) {
    data class Result(
        val popularity: Double, // 154.651
        val voteCount: Int, // 944
        val video: Boolean, // false
        val posterPath: String, // /AwsewPKXebJvtl4EU5Dx5uBCW6G.jpg
        val id: Int, // 448119
        val adult: Boolean, // false
        val backdropPath: String, // /xcUf6yIheo78btFqihlRLftdR3M.jpg
        val originalLanguage: Language, // en
        val originalTitle: String, // Dolittle
        val genreIds: List<Int>,
        val title: String, // Удивительное путешествие доктора Дулиттла
        val voteAverage: Double, // 6.8
        val overview: String, // Семь лет назад доктор Дулиттл, прославленный врач-ветеринар, живущий в викторианской Англии, потерял свою жену. Теперь он ведет затворнический образ жизни, скрывшись за высокими стенами своего поместья. Экзотические животные из его коллекции — его единственная компания. Но когда неизвестная болезнь становится угрозой для жизни юной королевы, доктору Дулиттлу приходится покинуть свое убежище и отправиться в невероятное путешествие к мифическому острову. В поисках лекарства для королевы он вступит в схватку с беспощадными врагами, познакомится с диковинными существами и будет вынужден проявить недюжинное мужество и смекалку.
        val releaseDate: Date // 2020-01-01
    )
}