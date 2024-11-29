package com.jhtest.storibanktest.ui.authentication.splash.compose


import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jhtest.storibanktest.ui.authentication.navigation.models.SplashNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.UiAction
import com.jhtest.storibanktest.ui.viewmodels.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

private const val MILLIS_200 = 200L
private const val MILLIS_3000 = 3000L

@Composable
internal fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel(),
    onAction: (UiAction) -> Unit
) {
    val splashState by splashViewModel.splashState.collectAsStateWithLifecycle()

    splashState.isUserLogged?.let { value ->
        LaunchedEffect(value) {
            delay(MILLIS_3000)
            if (value) {
                onAction(SplashNavAction.NavigateToHome)
            } else {
                onAction(SplashNavAction.NavigateToLogin)
            }
        }
    }

    splashState.error?.let { error ->
        LaunchedEffect(error) {
            onAction(SplashNavAction.NavigateToLogin)
        }
    }

    val systemUiController: SystemUiController = rememberSystemUiController()
    val backgroundColor = MaterialTheme.colorScheme.primary

    var showLogo by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        if (SDK_INT <= TIRAMISU) {
            delay(MILLIS_200)
            showLogo = true
            delay(MILLIS_3000)
            showLogo = false
            delay(MILLIS_200)
        }
    }

    DisposableEffect(systemUiController) {
        systemUiController.isNavigationBarVisible = false
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            transformColorForLightContent = { backgroundColor }
        )
        onDispose {}
    }
}