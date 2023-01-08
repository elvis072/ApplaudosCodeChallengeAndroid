package com.example.applaudoscodechallengeandroid.domain.repository

import com.example.applaudoscodechallengeandroid.domain.model.TvShow

enum class TvShowType {
    TOP_RATED,
    POPULAR,
    ON_THE_AIR,
    AIRING_TODAY
}

interface TvShowRepository {
    suspend fun getTvShows(type: TvShowType, page: Int) : List<TvShow>
}