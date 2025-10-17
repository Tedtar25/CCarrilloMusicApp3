package com.cesarcarrillo.ccarrillomusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cesarcarrillo.ccarrillomusicapp.screens.home.HomeScreen
import com.cesarcarrillo.ccarrillomusicapp.screens.detail.DetailScreen
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onAlbumClick = { album ->
                    // Guardamos el álbum y navegamos
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("album", album)
                    navController.navigate("detail")
                }
            )
        }

        composable("detail") {
            val album = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Album>("album")

            if (album != null) {
                DetailScreen(album)
            }
        }
    }
}
