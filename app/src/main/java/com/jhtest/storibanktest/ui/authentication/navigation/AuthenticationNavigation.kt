package com.jhtest.storibanktest.ui.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhtest.storibanktest.ui.authentication.navigation.models.AuthenticationScreens
import com.jhtest.storibanktest.ui.authentication.navigation.models.SplashNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.UiAction
import com.jhtest.storibanktest.ui.authentication.splash.compose.SplashScreen

@Composable
fun AuthenticationNavigation(
    onAction: (UiAction) -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AuthenticationScreens.SplashScreen.name
    ) {
        composable(AuthenticationScreens.SplashScreen.name) {
            SplashScreen { uiAction ->
                if (uiAction is SplashNavAction.NavigateToLogin) {
                    navController.navigate(AuthenticationScreens.LoginScreen.name)
                } else {
                    onAction(uiAction)
                }
            }
        }

        composable(AuthenticationScreens.LoginScreen.name) {

        }

        composable(AuthenticationScreens.SignUpScreen.name) {

        }

        composable(AuthenticationScreens.FaceIdScreen.name) {

        }

        composable(AuthenticationScreens.ErrorScreen.name) {

        }

        composable(AuthenticationScreens.SuccessScreen.name) {

        }
    }
}