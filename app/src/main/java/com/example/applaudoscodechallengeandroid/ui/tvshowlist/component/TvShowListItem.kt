package com.example.applaudoscodechallengeandroid.ui.tvshowlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.example.applaudoscodechallengeandroid.common.Constants
import com.example.applaudoscodechallengeandroid.domain.model.TvShow
import com.example.applaudoscodechallengeandroid.R
import com.example.applaudoscodechallengeandroid.ui.common.component.RatingBar
import com.example.applaudoscodechallengeandroid.ui.theme.ApplaudosCodeChallengeAndroidTheme

@Composable
fun TvShowListItem(
    tvShow: TvShow,
    onItemClick: (TvShow) -> Unit
) {
    Card(
        modifier = Modifier.clickable { onItemClick(tvShow) },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.ITEM_LIST_IMAGE_BASE_URL + tvShow.posterPath)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.image_placeholder),
            contentDescription = stringResource(R.string.tv_show_item_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = tvShow.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 5.dp)
            ) {
                val rating = remember { tvShow.voteAverage.toDouble() % 5 }
                RatingBar(
                    rating = rating,
                    starsColor = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = String.format("%.1f", rating),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Preview
@Composable
private fun TvShowListItemPreview() {
    val tvShow = TvShow(
        id = 119051,
        name = "Wednesday",
        posterPath = "/9PFonBhy4cQy7Jz20NpMygczOkv.jpg",
        voteAverage = 8.7f
    )
    ApplaudosCodeChallengeAndroidTheme {
        TvShowListItem(tvShow) {}
    }
}