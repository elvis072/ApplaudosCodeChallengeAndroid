package com.example.applaudoscodechallengeandroid.ui.tvshowdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applaudoscodechallengeandroid.R
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.domain.model.TvShowDetail
import com.example.applaudoscodechallengeandroid.ui.common.component.RatingBar
import com.example.applaudoscodechallengeandroid.ui.tvshowdetail.component.TvShowSeasonItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowDetailScreen(
    navController: NavController,
    tvShowDetailViewModel: TvShowDetailViewModel = hiltViewModel()
) {
    val detailState = tvShowDetailViewModel.state.value

    // check for error, empty and loading states
    if (detailState.detail == null || detailState.error.isNotEmpty() || detailState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (detailState.isLoading) {
                CircularProgressIndicator()
            } else {
                val message = if (detailState.error.isNotEmpty()) {
                    detailState.error
                } else {
                    stringResource(R.string.no_data)
                }
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
        return
    }

    val detail = detailState.detail
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text =  detail.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = stringResource(R.string.top_appbar_back_icon)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {
        val childrenModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(vertical = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                Header(detail = detail)
            }
            item {
                Column(modifier = childrenModifier) {
                    Text(
                        text = stringResource(R.string.summary),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.displaySmall,
                    )
                    Text(
                        text = detail.overview,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
            items(detail.seasons) {
                TvShowSeasonItem(season = it, modifier = childrenModifier)
            }
        }
    }
}

@Composable
private fun Header(detail: TvShowDetail) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.ITEM_DETAIL_IMAGE_BASE_URL + detail.posterPath)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.image_placeholder),
            contentDescription = stringResource(R.string.tv_show_detail_image),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(.7f)
        )
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
        ) {
            val rating = remember { detail.voteAverage % 5 }
            Text(
                text = detail.originalName,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = detail.name,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineLarge,
            )
            RatingBar(
                rating = rating,
                starsColor = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}