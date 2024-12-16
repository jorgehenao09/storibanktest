package com.jhtest.storibanktest.ui.home.homeScreen.compose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val userName by homeViewModel.userInfoState.collectAsStateWithLifecycle()
    val userTransactions by homeViewModel.userTransactionsState.collectAsStateWithLifecycle()

    BackHandler {}

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        HomeWelcomeMessage(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 40.dp, bottom = 16.dp),
            userName,
        )

        HomeTips(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
        )

        AnimatedVisibility(userTransactions.isLoading) {
            HomeTransactionsShimmer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 16.dp),
            )
        }

        AnimatedVisibility(userTransactions.messageError.isNotBlank()) {
            Text(
                modifier =
                    Modifier
                        .padding(vertical = 64.dp, horizontal = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.home_view_transactions_empty),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            )
        }

        AnimatedVisibility(userTransactions.isSuccess != null) {
            HomeTransactions(
                modifier = Modifier.padding(horizontal = 16.dp),
                userTransactions = userTransactions.isSuccess!!,
            )
        }
    }
}
