package com.example.applaudoscodechallengeandroid.data.remote.service

import com.example.applaudoscodechallengeandroid.data.remote.dto.PageResultDto
import com.example.applaudoscodechallengeandroid.data.remote.dto.TvShowDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowService {
    @GET("tv/top_rated")
    suspend fun getTopRatedShows(@Query("page") page: Int) : PageResultDto<TvShowDto>

    @GET("tv/popular")
    suspend fun getPopularShows(@Query("page") page: Int) : PageResultDto<TvShowDto>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirShows(@Query("page") page: Int) : PageResultDto<TvShowDto>

    @GET("tv/airing_today")
    suspend fun getAiringTodayShows(@Query("page") page: Int) : PageResultDto<TvShowDto>
}