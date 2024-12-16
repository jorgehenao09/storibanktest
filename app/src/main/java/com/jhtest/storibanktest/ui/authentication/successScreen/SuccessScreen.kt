package com.jhtest.storibanktest.ui.authentication.successScreen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.SuccessNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import kotlinx.coroutines.delay

private const val MILLIS_200 = 200L
private const val MILLIS_1000 = 1000L
private const val MILLIS_2000 = 2000L
private const val MILLIS_3000 = 3000L

@Composable
fun SuccessScreen(onAction: (UiAction) -> Unit) {
    var showTitle by remember { mutableStateOf(false) }
    var showLogo by remember { mutableStateOf(false) }
    var showWelcome by remember { mutableStateOf(false) }

    BackHandler {}

    LaunchedEffect(key1 = Unit) {
        delay(MILLIS_200)
        showTitle = true
        delay(MILLIS_1000)
        showLogo = true
        delay(MILLIS_2000)
        showWelcome = true
        delay(MILLIS_3000)
        onAction(SuccessNavAction.NavigateToHome)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(showTitle) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.authentication_view_success_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondaryContainer,
                fontWeight = FontWeight.Bold,
            )
        }
        AnimatedVisibility(showLogo) {
            Image(
                modifier =
                    Modifier
                        .size(200.dp),
                painter = painterResource(id = R.drawable.ic_success),
                contentDescription = "success_logo",
            )
        }
        AnimatedVisibility(showWelcome) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.authentication_view_success_welcome),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
