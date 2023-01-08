package com.example.applaudoscodechallengeandroid.ui.tvshowlist

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.data.datasource.TvShowPagingSource
import com.example.applaudoscodechallengeandroid.domain.model.TvShow
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TvShowListViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository,
): ViewModel() {

    private val _state = MutableStateFlow(TvShowListState())
    val state = _state.asStateFlow()

    val tvShows = Pager(
        config = PagingConfig(
            pageSize = Constants.NETWORK_PAGE_SIZE,
            initialLoadSize = Constants.NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            TvShowPagingSource(tvShowRepository, _state.value.showType)
        }
    ).flow

    fun selectTvShowType(type: TvShowType) {
        _state.value = _state.value.copy(showType = type)
    }

    data class TvShowListState(
        val showType: TvShowType = TvShowType.TOP_RATED
    )
}