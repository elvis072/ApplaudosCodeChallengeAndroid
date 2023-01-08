package com.example.applaudoscodechallengeandroid.data.repository

import com.example.applaudoscodechallengeandroid.data.remote.service.TvShowService
import com.example.applaudoscodechallengeandroid.domain.model.TvShow
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowType
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowService: TvShowService
) : TvShowRepository {
    override suspend fun getTvShows(type: TvShowType, page: Int): List<TvShow> {
        val result = when (type) {
            TvShowType.TOP_RATED -> tvShowService.getTopRatedShows(page)
            TvShowType.POPULAR -> tvShowService.getPopularShows(page)
            TvShowType.ON_THE_AIR -> tvShowService.getOnTheAirShows(page)
            TvShowType.AIRING_TODAY -> tvShowService.getAiringTodayShows(page)
        }

        return result.results.map {
            TvShow(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage
            )
        }
    }
}