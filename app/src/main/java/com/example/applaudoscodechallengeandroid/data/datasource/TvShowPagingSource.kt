package com.example.applaudoscodechallengeandroid.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.domain.model.TvShow
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowType
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TvShowPagingSource @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    private val type: TvShowType
): PagingSource<Int, TvShow>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        val position = params.key ?: Constants.STARTING_PAGE_INDEX
        return try {
            val shows = tvShowRepository.getTvShows(type, position)
            val nextKey = if (shows.isEmpty()) null else position.plus(1)
            LoadResult.Page(
                data = shows,
                prevKey = if (position == Constants.STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}