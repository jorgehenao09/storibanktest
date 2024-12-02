package com.jhtest.storibanktest.ui.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhtest.storibanktest.ui.authentication.login.compose.LoginScreen
import com.jhtest.storibanktest.ui.authentication.navigation.models.AuthenticationScreens
import com.jhtest.storibanktest.ui.authentication.navigation.models.LoginNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.SplashNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.UiAction
import com.jhtest.storibanktest.ui.authentication.signUp.compose.SignUpScreen
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
                }
                onAction(uiAction)
            }
        }

        composable(AuthenticationScreens.LoginScreen.name) {
            LoginScreen { uiAction ->
                when(uiAction) {
                    is LoginNavAction.NavigateToSignUp -> {
                        navController.navigate(AuthenticationScreens.SignUpScreen.name)
                    }

                    is LoginNavAction.NavigateToErrorScreen -> {
                        navController.navigate(AuthenticationScreens.ErrorScreen.name)
                    }

                    else -> onAction(uiAction)
                }

                onAction(uiAction)
            }
        }

        composable(AuthenticationScreens.SignUpScreen.name) {
            SignUpScreen {
                onAction(it)
            }
        }

        composable(AuthenticationScreens.FaceIdScreen.name) {

        }

        composable(AuthenticationScreens.ErrorScreen.name) {

        }

        composable(AuthenticationScreens.SuccessScreen.name) {

        }
    }
}