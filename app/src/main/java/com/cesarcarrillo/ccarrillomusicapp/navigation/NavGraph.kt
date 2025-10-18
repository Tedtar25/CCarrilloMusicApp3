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

        // Pantalla principal (Home)
        composable("home") {
            HomeScreen(
                onAlbumClick = { album ->
                    // Cuando se hace clic en un álbum, actualizamos el player
                    playerViewModel.setCurrentAlbum(album)
                    navController.navigate("detail/${album.id}")
                },
                playerViewModel = playerViewModel  //<- AQUI ESTA EL ERROR
            )
        }

        // Pantalla de detalle
        composable("detail/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            DetailScreen(
                albumId = albumId,
                playerViewModel = playerViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
