package com.cesarcarrillo.ccarrillomusicapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.cesarcarrillo.ccarrillomusicapp.components.Player
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onAlbumClick: (Album) -> Unit,
    playerViewModel: PlayerViewModel
)
 {
    val uiState by viewModel.uiState.collectAsState()
    var currentAlbum by rememberSaveable { mutableStateOf<Album?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        when (uiState) {
            is AlbumUiState.Loading -> LoadingState()
            is AlbumUiState.Error -> ErrorState((uiState as AlbumUiState.Error).message)
            is AlbumUiState.Success -> {
                val albums = (uiState as AlbumUiState.Success).albums

                if (currentAlbum == null && albums.isNotEmpty()) {
                    currentAlbum = albums.first()
                }

                HomeContent(
                    albums = albums,
                    onAlbumClick = { album ->
                        currentAlbum = album
                        onAlbumClick(album)
                    },
                    modifier = Modifier.padding(bottom = 80.dp)
                )
            }
        }

        Player(
            album = currentAlbum,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(72.dp)
        )
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error: $message", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun HomeContent(
    albums: List<Album>,
    onAlbumClick: (Album) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Good Morning!",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Beto Carrillo",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Albums",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(albums.size) { index ->
                AlbumCard(album = albums[index], onAlbumClick = onAlbumClick)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Recently Played",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(albums.size) { index ->
                AlbumListItem(album = albums[index], onAlbumClick = onAlbumClick)
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album, onAlbumClick: (Album) -> Unit) {
    Card(
        onClick = { onAlbumClick(album) },
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(album.image),
                contentDescription = album.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            )
            Text(
                text = album.title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun AlbumListItem(album: Album, onAlbumClick: (Album) -> Unit) {
    Card(
        onClick = { onAlbumClick(album) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(album.image),
                contentDescription = album.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = album.title, fontWeight = FontWeight.Bold)
                Text(text = album.artist, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
