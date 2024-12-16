package com.jhtest.storibanktest.ui.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.home.homeScreen.compose.HomeScreen
import com.jhtest.storibanktest.ui.home.navigation.models.HomeScreens

@Composable
fun HomeNavigation(onAction: (UiAction) -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreens.HomeScreen.name,
    ) {
        composable(HomeScreens.HomeScreen.name) {
            HomeScreen()
        }
    }
}
