package com.cesarcarrillo.ccarrillomusicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album
import com.cesarcarrillo.ccarrillomusicapp.ui.theme.PurpleDark
import com.cesarcarrillo.ccarrillomusicapp.ui.theme.PurplePrimary

@Composable
fun Player(album: Album?, modifier: Modifier = Modifier) {
    if (album == null) return

    Box(
        modifier = modifier
            .background(Color(0xFF2B142F), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))

            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(album.image),
                    contentDescription = album.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(album.title, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(album.artist, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                }
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(44.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Play", tint = PurplePrimary)
            }
        }
    }
}
