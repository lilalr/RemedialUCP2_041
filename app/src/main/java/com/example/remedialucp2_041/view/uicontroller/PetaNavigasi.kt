package com.example.remedialucp2_041.view.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remedialucp2_041.view.HalamanEdit
import com.example.remedialucp2_041.view.HalamanEntryBuku
import com.example.remedialucp2_041.view.HalamanEntryKategori
import com.example.remedialucp2_041.view.HalamanHome
import com.example.remedialucp2_041.view.route.DestinasiEdit
import com.example.remedialucp2_041.view.route.DestinasiEntry
import com.example.remedialucp2_041.view.route.DestinasiEntryKategori
import com.example.remedialucp2_041.view.route.DestinasiHome

@Composable
fun HostNavigasi(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HalamanHome(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToEntryKategori = { navController.navigate(DestinasiEntryKategori.route) },
                onDetailClick = { id ->
                    navController.navigate("${DestinasiEdit.route}/$id")
                }
            )
        }

        composable(DestinasiEntry.route) {
            HalamanEntryBuku(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(DestinasiEntryKategori.route) {
            HalamanEntryKategori(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.BUKU_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            HalamanEdit(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}