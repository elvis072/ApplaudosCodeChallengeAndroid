package com.example.applaudoscodechallengeandroid.ui.tvshowlist

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.applaudoscodechallengeandroid.R
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.domain.repository.TvShowType
import com.example.applaudoscodechallengeandroid.ui.Screen
import com.example.applaudoscodechallengeandroid.ui.tvshowlist.component.TvShowListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowListScreen(
    navController: NavController,
    viewModel: TvShowListViewModel = hiltViewModel(),
) {
    val tvShowFilters = stringArrayResource(R.array.tv_show_types)
    val tvShows = viewModel.tvShows.collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()

    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.tv_shows),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall,
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp, 15.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TvShowType.values().forEach {
                FilterChip(
                    label = {
                        Text(
                            text = tvShowFilters[it.ordinal],
                            style = MaterialTheme.typography.titleLarge,
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        labelColor = MaterialTheme.colorScheme.onSurface,
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    border = null,
                    shape = MaterialTheme.shapes.large,
                    enabled = tvShows.loadState.refresh is LoadState.NotLoading,
                    selected = it == state.showType,
                    onClick = {
                        if (it != state.showType) {
                            viewModel.selectTvShowType(it)
                            tvShows.refresh()
                        }
                    }
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(15.dp, 15.dp)
        ) {
            items(tvShows.itemCount) { index ->
                tvShows[index]?.let { tvShow ->
                    TvShowListItem(tvShow = tvShow, onItemClick = {
                        navController.navigate(Screen.TvShowDetail.route + "/${it.id}")
                    })
                }
            }
        }

        // Show progress indicator when loading items
        if (tvShows.loadState.refresh is LoadState.Loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

        // Show errors
        if (tvShows.loadState.refresh is LoadState.Error || tvShows.loadState.append is LoadState.Error) {
            val errorMessage = if (tvShows.loadState.refresh is LoadState.Error)
                (tvShows.loadState.refresh as LoadState.Error).error.message
            else
                (tvShows.loadState.append as LoadState.Error).error.message

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ) {
                    Text(
                        text = errorMessage ?: stringResource(R.string.error_message),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}