package com.cesarcarrillo.ccarrillomusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cesarcarrillo.ccarrillomusicapp.screens.detail.DetailScreen
import com.cesarcarrillo.ccarrillomusicapp.screens.home.HomeScreen
import com.cesarcarrillo.ccarrillomusicapp.screens.home.PlayerViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    playerViewModel: PlayerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // 🏠 Pantalla principal
        composable("home") {
            HomeScreen(
                onAlbumClick = { album ->
                    playerViewModel.setCurrentAlbum(album)
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("album", album)
                    navController.navigate("detail")
                },
                playerViewModel = playerViewModel
            )
        }

        // 💿 Pantalla de detalle
        composable("detail") {
            val album =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<com.cesarcarrillo.ccarrillomusicapp.data.model.Album>("album")

            album?.let {
                DetailScreen(
                    album = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
