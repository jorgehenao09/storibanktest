package com.jhtest.storibanktest.ui.authentication.faceId.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.R

@Composable
internal fun FaceIdHeader(modifier: Modifier = Modifier) {
    Column(
        modifier =
            modifier
                .fillMaxWidth(),
    ) {
        Text(
            modifier =
                Modifier
                    .fillMaxWidth(),
            text = stringResource(R.string.authentication_view_face_id_header),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Text(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 24.dp, end = 24.dp),
            text = stringResource(R.string.authentication_view_face_id_description),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}
