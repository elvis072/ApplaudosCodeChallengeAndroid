package com.example.applaudoscodechallengeandroid.ui.tvshowdetail.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applaudoscodechallengeandroid.R
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.domain.model.Season
import com.example.applaudoscodechallengeandroid.ui.theme.ApplaudosCodeChallengeAndroidTheme

@Composable
fun TvShowSeasonItem(
    modifier: Modifier = Modifier,
    season: Season
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(150.dp)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constants.ITEM_LIST_IMAGE_BASE_URL + season.posterPath)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.image_placeholder),
                contentDescription = stringResource(R.string.tv_show_item_image),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth(.3f)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = season.name,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(R.string.tv_show_season_episodes, season.episodeCount),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelMedium,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = season.overview,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TvShowSeasonItemPreview() {
    val season = Season(
        id = 182137,
        name = "Season 1",
        overview = "As the rest of the team face their worst fears, Noodle Burger Boy. ",
        episodeCount = 8,
        posterPath = "/ajACh2JtjPOS2jJFhuD30gI1o8a.jpg",
    )

    ApplaudosCodeChallengeAndroidTheme {
        TvShowSeasonItem(season = season)
    }
}