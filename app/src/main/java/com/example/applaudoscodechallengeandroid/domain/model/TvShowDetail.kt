package com.example.applaudoscodechallengeandroid.domain.model

data class TvShowDetail(
    val id: Int,
    val name: String,
    val originalName: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String,
    val seasons: List<Season>,
)