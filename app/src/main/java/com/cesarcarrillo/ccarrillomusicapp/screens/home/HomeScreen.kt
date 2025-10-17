package com.cesarcarrillo.ccarrillomusicapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
<<<<<<< HEAD
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
=======
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
<<<<<<< HEAD
import com.cesarcarrillo.ccarrillomusicapp.components.Player
=======
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onAlbumClick: (Album) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
<<<<<<< HEAD
    var currentAlbum by rememberSaveable { mutableStateOf<Album?>(null) }
=======
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        when (uiState) {
            is AlbumUiState.Loading -> LoadingState()
            is AlbumUiState.Error -> ErrorState((uiState as AlbumUiState.Error).message)
<<<<<<< HEAD
            is AlbumUiState.Success -> {
                val albums = (uiState as AlbumUiState.Success).albums

                LaunchedEffect(albums) {
                    if (currentAlbum == null && albums.isNotEmpty()) {
                        currentAlbum = albums.first()
                    }
                }

                HomeContent(
                    albums = albums,
                    onAlbumClick = { album ->
                        currentAlbum = album
                    },
                    modifier = Modifier.padding(bottom = 90.dp)
                )
            }
        }

        Player(
            album = currentAlbum,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(72.dp),
            onAlbumClick = { clickedAlbum ->
                currentAlbum = clickedAlbum
            }
        )
=======
            is AlbumUiState.Success -> HomeContent(
                albums = (uiState as AlbumUiState.Success).albums,
                onAlbumClick = onAlbumClick
            )
        }
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
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

<<<<<<< HEAD
// 🔹 Aquí agregamos el parámetro `modifier` correctamente
@Composable
fun HomeContent(
    albums: List<Album>,
    onAlbumClick: (Album) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()) // 🔹 permite que toda la columna sea scrolleable
=======
@Composable
fun HomeContent(albums: List<Album>, onAlbumClick: (Album) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
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
<<<<<<< HEAD
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
=======
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
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
<<<<<<< HEAD
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            albums.forEach { album ->
                AlbumListItem(album = album, onAlbumClick = onAlbumClick)
=======
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(albums.size) { index ->
                AlbumListItem(album = albums[index], onAlbumClick = onAlbumClick)
>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
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
                painter = rememberAsyncImagePainter(album.image_url),
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
                painter = rememberAsyncImagePainter(album.image_url),
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
<<<<<<< HEAD
=======

@Composable
fun MiniPlayer(album: Album?) {
    if (album != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(album.image_url),
                    contentDescription = album.title,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = album.title, fontWeight = FontWeight.Bold)
                    Text(text = album.artist, style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* TODO: play/pause */ }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play"
                    )
                }
            }
        }
    }
}

>>>>>>> 7fc119106c162b24f57c8bd5d7e4cd5b3b545b70
