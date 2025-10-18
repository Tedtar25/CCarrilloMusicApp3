package com.cesarcarrillo.ccarrillomusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
//import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.cesarcarrillo.ccarrillomusicapp.components.Player
import com.cesarcarrillo.ccarrillomusicapp.navigation.AppNavGraph
import com.cesarcarrillo.ccarrillomusicapp.screens.home.PlayerViewModel
import com.cesarcarrillo.ccarrillomusicapp.ui.theme.CCarrilloMusicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CCarrilloMusicAppTheme {
                val navController = rememberNavController()
                val playerViewModel: PlayerViewModel = viewModel()

                Box(modifier = Modifier.fillMaxSize()) {
                    AppNavGraph(
                        navController = navController,
                        playerViewModel = playerViewModel
                    )

                    val currentAlbum by playerViewModel.currentAlbum.collectAsState()
                    if (currentAlbum != null) {
                        Player(
                            album = currentAlbum,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .height(72.dp)
                                .padding(bottom = 0.dp)
                        )
                    }
                }
            }
        }
    }
}
