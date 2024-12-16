package com.jhtest.storibanktest.ui.home.homeScreen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes
import com.jhtest.storibanktest.ui.theme.components.utils.shimmerEffect

@Composable
internal fun HomeTransactionsShimmer(modifier: Modifier = Modifier) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
    ) {
        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 16.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp),
        )

        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 16.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp),
        )

        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 16.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp),
        )

        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 16.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeTransactionsShimmerPreview() {
    HomeTransactionsShimmer()
}
