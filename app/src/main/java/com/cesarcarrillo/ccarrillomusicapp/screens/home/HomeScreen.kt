package com.cesarcarrillo.ccarrillomusicapp.screens.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cesarcarrillo.ccarrillomusicapp.components.Player
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album
import com.cesarcarrillo.ccarrillomusicapp.ui.theme.PurpleDark
import com.cesarcarrillo.ccarrillomusicapp.ui.theme.PurplePrimary

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onAlbumClick: (Album) -> Unit,
    playerViewModel: PlayerViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var currentAlbum by rememberSaveable { mutableStateOf<Album?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4FB)) // Fondo gris claro suave
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
fun HomeContent(
    albums: List<Album>,
    onAlbumClick: (Album) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HeaderSection()

        Spacer(modifier = Modifier.height(20.dp))

        SectionTitle("Albums", onSeeMoreClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(albums.size) { index ->
                AlbumCard(album = albums[index], onAlbumClick = onAlbumClick)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle("Recently Played", onSeeMoreClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(albums.size) { index ->
                AlbumListItem(album = albums[index], onAlbumClick = onAlbumClick)
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F4FB))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Card(
            shape = RoundedCornerShape(40.dp),
            colors = CardDefaults.cardColors(containerColor = PurplePrimary),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Íconos arriba
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                }

                // Texto centrado verticalmente
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = "Good Morning!",
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Beto Carrillo",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String, onSeeMoreClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        TextButton(onClick = onSeeMoreClick) {
            Text("See more", color = PurplePrimary)
        }
    }
}

@Composable
fun AlbumCard(album: Album, onAlbumClick: (Album) -> Unit) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(210.dp)
            .clip(RoundedCornerShape(24.dp))
            .shadow(6.dp, RoundedCornerShape(24.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter(album.image),
            contentDescription = album.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color(0xCC000000))
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        ) {
            Text(album.title, color = Color.White, fontWeight = FontWeight.Bold)
            Text(album.artist, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        }
        IconButton(
            onClick = { onAlbumClick(album) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .size(38.dp)
                .background(PurplePrimary, CircleShape)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.White)
        }
    }
}

@Composable
fun AlbumListItem(album: Album, onAlbumClick: (Album) -> Unit) {
    Card(
        onClick = { onAlbumClick(album) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
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
                Text(text = album.title, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(
                    text = "${album.artist} • Popular Song",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = Color.Gray)
        }
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PurplePrimary)
    }
}

@Composable
fun ErrorState(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
