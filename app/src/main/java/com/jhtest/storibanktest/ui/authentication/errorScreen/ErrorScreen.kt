package com.jhtest.storibanktest.ui.authentication.errorScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.ErrorNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.theme.components.PrimaryButton
import kotlinx.coroutines.delay

private const val MILLIS_100 = 100L
private const val MILLIS_500 = 500L

@Composable
fun ErrorScreen(onAction: (UiAction) -> Unit) {
    var showContent by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(MILLIS_100)
        showContent = true
        delay(MILLIS_500)
        showButton = true
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (content, button) = createRefs()

        AnimatedVisibility(
            visible = showContent,
            modifier =
                Modifier.constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier =
                        Modifier
                            .size(150.dp),
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "error_logo",
                )

                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                    text = stringResource(R.string.authentication_view_error_description),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        AnimatedVisibility(
            visible = showButton,
            modifier =
                Modifier.constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ) {
            PrimaryButton(
                modifier = Modifier.padding(bottom = 56.dp),
                textValue = stringResource(R.string.label_back),
            ) {
                onAction(ErrorNavAction.NavigateBack)
            }
        }
    }
}
