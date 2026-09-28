package com.shiki.ott_ai.domain.model


data class ResultUpcoming(
    val adult: Boolean,

    val backdroppath: String,

    val genreids: List<Int>,

    val id: Int,

    val originallanguage: String,

    val originaltitle: String,

    val overview: String,

    val popularity: Double,

    val posterPath: String,

    val releasedate: String,

    val title: String,

    val video: Boolean,

    val voteaverage: Double,

        val votecount: Int
)