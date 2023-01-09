package com.example.applaudoscodechallengeandroid.ui.tvshowdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.domain.model.TvShowDetail
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.applaudoscodechallengeandroid.common.Result

@HiltViewModel
class TvShowDetailViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _state = mutableStateOf(TvShowDetailState())
    val state : State<TvShowDetailState> = _state

    init {
        savedStateHandle.get<Int>(Constants.TV_SHOW_ID_KEY)?.let {
            getDetail(it)
        }
    }

    private fun getDetail(showId: Int) {
        tvShowRepository.getTvShowDetail(showId).onEach { result ->
            when (result) {
                is Result.Success -> _state.value = TvShowDetailState(isLoading = false, detail = result.data)
                is Result.Error -> _state.value = TvShowDetailState(isLoading = false, error = result.message!!)
                is Result.Loading -> _state.value = TvShowDetailState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    data class TvShowDetailState(
        val isLoading: Boolean = false,
        val detail: TvShowDetail? = null,
        val error: String = ""
    )
}