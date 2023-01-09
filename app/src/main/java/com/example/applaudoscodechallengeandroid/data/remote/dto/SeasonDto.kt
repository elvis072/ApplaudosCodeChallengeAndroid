package com.example.applaudoscodechallengeandroid.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SeasonDto(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int
)