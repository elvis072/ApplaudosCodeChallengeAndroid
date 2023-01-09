package com.example.applaudoscodechallengeandroid.domain.repository

import com.example.applaudoscodechallengeandroid.domain.model.TvShow
import com.example.applaudoscodechallengeandroid.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow
import com.example.applaudoscodechallengeandroid.common.Result

enum class TvShowType {
    TOP_RATED,
    POPULAR,
    ON_THE_AIR,
    AIRING_TODAY
}

interface TvShowRepository {
    suspend fun getTvShows(type: TvShowType, page: Int) : List<TvShow>
    fun getTvShowDetail(showId: Int) : Flow<Result<TvShowDetail>>
}