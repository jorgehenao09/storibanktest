package com.jhtest.storibanktest.ui.authentication.login.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes
import com.jhtest.storibanktest.ui.theme.components.utils.shimmerEffect

@Composable
internal fun LoginShimmer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 50.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(top = 16.dp)
                .background(Color.Transparent, Shapes.medium)
                .shimmerEffect(radius = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 16.dp)
                .background(Color.Transparent, Shapes.medium)
                .shimmerEffect(radius = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 16.dp)
                .background(Color.Transparent, Shapes.medium)
                .shimmerEffect(radius = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 16.dp)
                .background(Color.Transparent, Shapes.medium)
                .shimmerEffect(radius = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(top = 16.dp)
                .background(Color.Transparent, Shapes.medium)
                .shimmerEffect(radius = 8.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 16.dp)
                .background(Color.Transparent, Shapes.medium)
                .shimmerEffect(radius = 8.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginShimmerPreview() {
    LoginShimmer()
}