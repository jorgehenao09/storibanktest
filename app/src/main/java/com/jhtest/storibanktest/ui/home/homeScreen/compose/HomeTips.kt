package com.jhtest.storibanktest.ui.home.homeScreen.compose

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.R

@Composable
internal fun HomeTips(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val tips = getTips(context)

    ElevatedCard(
        modifier =
            modifier
                .fillMaxWidth(),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            ),
        shape = RoundedCornerShape(12.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            Text(
                text = stringResource(R.string.home_view_how_to_handle_your_account),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
            tips.forEach { tip ->
                Text(
                    text = "\u2022 $tip",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = Color.White,
                )
            }
        }
    }
}

private fun getTips(context: Context): List<String> {
    return listOf(
        context.getString(R.string.home_view_tip_positive_value),
        context.getString(R.string.home_view_tip_avoid_unrecognizable_transfers),
        context.getString(R.string.home_view_tip_check_movements),
    )
}
