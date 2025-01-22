package com.jhtest.storibanktest.ui.authentication

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jhtest.storibanktest.ui.authentication.navigation.AuthenticationNavigation
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.LoginNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.SplashNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.SuccessNavAction
import com.jhtest.storibanktest.ui.home.HomeActivity
import com.jhtest.storibanktest.ui.theme.StoriBankTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : ComponentActivity() {
    private var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (SDK_INT > TIRAMISU) {
            // This code is used to avoid the jump showed in Android version up to 33
            installSplashScreen().setKeepOnScreenCondition {
                keepSplashOpened
            }
        }

        setContent {
            StoriBankTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AuthenticationNavigation { uiAction ->
                        when (uiAction) {
                            is SplashNavAction.NavigateToHome,
                            LoginNavAction.NavigateToHome,
                            SuccessNavAction.NavigateToHome -> {
                                keepSplashOpened = false
                                launchHomeActivity()
                            }

                            else -> {
                                keepSplashOpened = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun launchHomeActivity() {
        startActivity(HomeActivity.newIntent(from = this))
        this@AuthenticationActivity.finish()
    }
}
