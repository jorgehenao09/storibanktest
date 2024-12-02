package com.jhtest.storibanktest.ui.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhtest.storibanktest.ui.authentication.faceId.compose.FaceIdScreen
import com.jhtest.storibanktest.ui.authentication.login.compose.LoginScreen
import com.jhtest.storibanktest.ui.authentication.navigation.models.AuthenticationScreens
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.LoginNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.SignUpNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.SplashNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.authentication.signUp.compose.SignUpScreen
import com.jhtest.storibanktest.ui.authentication.splash.compose.SplashScreen
import com.jhtest.storibanktest.ui.viewmodels.SignUpViewModel

@Composable
fun AuthenticationNavigation(
    onAction: (UiAction) -> Unit
) {
    val signUpViewModel = hiltViewModel<SignUpViewModel>()
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
                when (uiAction) {
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
            SignUpScreen(signUpViewModel) { uiAction ->
                if (uiAction is SignUpNavAction.NavigateToFaceId) {
                    navController.navigate(AuthenticationScreens.FaceIdScreen.name)
                } else {
                    onAction(uiAction)
                }
            }
        }

        composable(AuthenticationScreens.FaceIdScreen.name) {
            FaceIdScreen(signUpViewModel)
        }

        composable(AuthenticationScreens.ErrorScreen.name) {

        }

        composable(AuthenticationScreens.SuccessScreen.name) {

        }
    }
}