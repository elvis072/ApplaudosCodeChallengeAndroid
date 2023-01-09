package com.example.applaudoscodechallengeandroid.data.repository

import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.data.remote.service.TvShowService
import com.example.applaudoscodechallengeandroid.domain.model.TvShow
import com.example.applaudoscodechallengeandroid.domain.model.TvShowDetail
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowType
import com.example.applaudoscodechallengeandroid.common.Result
import com.example.applaudoscodechallengeandroid.domain.model.Season
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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

    override fun getTvShowDetail(showId: Int): Flow<Result<TvShowDetail>> = flow {
        try {
            emit(Result.Loading())
            val show = tvShowService.getTvShowDetail(showId)
            emit(Result.Success(TvShowDetail(
                id = show.id,
                name = show.name,
                originalName = show.originalName,
                overview = show.overview,
                voteAverage = show.voteAverage,
                posterPath = show.posterPath,
                seasons = show.seasons.map {
                    Season(
                        id = it.id,
                        name = it.name,
                        overview = it.overview,
                        posterPath = it.posterPath,
                        episodeCount = it.episodeCount
                    )
                }
            )))
        }
        catch (e: IOException) {
            emit(Result.Error(e.message ?: Constants.GENERIC_ERROR))
        } catch (e: HttpException) {
            emit(Result.Error(e.message ?: Constants.GENERIC_ERROR))
        }
    }
}