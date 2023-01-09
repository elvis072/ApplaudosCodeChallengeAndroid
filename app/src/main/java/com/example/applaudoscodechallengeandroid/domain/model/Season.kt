package com.example.applaudoscodechallengeandroid.domain.model

data class Season(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val episodeCount: Int,
)