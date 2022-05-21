package com.kuswand.movieapp.genre

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.kuswand.movieapp.R
import com.kuswand.movieapp.model.Genre
import com.kuswand.movieapp.utils.Constants.IMAGE_URL

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenreScreen(
    viewModel: GenreViewModel = GenreViewModel()
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val genres = listOf(
        Genre(28, "Action"),
        Genre(12, "Adventure"),
        Genre(16, "Animation"),
        Genre(35, "Comedy"),
        Genre(80, "Crime")
    )

    LazyColumn {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "120")
                    Text(text = "Movies")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "999")
                    Text(text = "Tv Series")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "15")
                    Text(text = "Favorite")
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            Text(
                text = "Now Playing",
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(18.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(movies) {
                    AsyncImage(
                        model = IMAGE_URL + "w342" + it?.backdropPath,
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
        stickyHeader {
            LazyRow(
                contentPadding = PaddingValues(18.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.background(MaterialTheme.colors.surface)
            ) {
                items(genres) {
                    Text(
                        text = it.title,
                        color = MaterialTheme.colors.surface,
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary)
                            .clickable { viewModel.getMovieByGenre(it.id) }
                            .padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(movies) {
            AsyncImage(
                model = IMAGE_URL + "w342" + it?.posterPath,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(24.dp)
                    .aspectRatio(1f)
            )
        }
    }
}