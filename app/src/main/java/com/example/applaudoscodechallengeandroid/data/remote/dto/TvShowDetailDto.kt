package com.example.applaudoscodechallengeandroid.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TvShowDetailDto(
    val id: Int,
    val name: String,
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    val homepage: String,
    @SerializedName("in_production")
    val inProduction: Boolean,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    val seasons: List<SeasonDto>,
    val status: String,
    val tagline: String,
    val type: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)