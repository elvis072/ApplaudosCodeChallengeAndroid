package com.example.applaudoscodechallengeandroid.repository

import com.example.applaudoscodechallengeandroid.data.remote.dto.PageResultDto
import com.example.applaudoscodechallengeandroid.data.remote.dto.TvShowDto
import com.example.applaudoscodechallengeandroid.data.remote.service.TvShowService
import com.example.applaudoscodechallengeandroid.data.repository.TvShowRepositoryImpl
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class TvShowRepositoryImplTest {
    private lateinit var repository: TvShowRepository
    private lateinit var tvShowService: TvShowService

    @Before
    fun setUp() {
        tvShowService = mock(TvShowService::class.java)
        repository = TvShowRepositoryImpl(tvShowService)
    }

    @Test
    fun `get tv shows with valid page, returns successful`(): Unit = runBlocking {
        val page = 1
        val tvShow = TvShowDto(
            id = 1,
            name = "",
            posterPath = "",
            overview = "",
            voteAverage = 0f,
            originalName = "",
            backdropPath = "",
            firstAirDate = "",
            genreIds = emptyList(),
            originalLanguage = "",
            originCountry = emptyList(),
            popularity = 0.0,
            voteCount = 0
        )
        val successResponse = PageResultDto(
            page = page,
            results = listOf(tvShow),
            totalResults = 5,
            totalPages = 5
        )
        `when`(tvShowService.getTopRatedShows(Mockito.anyInt())).thenReturn(successResponse)

        val result = repository.getTvShows(TvShowType.TOP_RATED, page)

        assertNotNull(result)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `get tv shows with invalid page, returns empty`(): Unit = runBlocking {
        val page = 0
        val successResponse = PageResultDto<TvShowDto>(
            page = page,
            results = emptyList(),
            totalResults = 0,
            totalPages = 0
        )
        `when`(tvShowService.getTopRatedShows(Mockito.anyInt())).thenReturn(successResponse)

        val result = repository.getTvShows(TvShowType.TOP_RATED, page)

        assertNotNull(result)
        assertTrue(result.isEmpty())
    }
}