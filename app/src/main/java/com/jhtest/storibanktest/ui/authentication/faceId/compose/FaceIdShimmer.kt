package com.jhtest.storibanktest.ui.authentication.faceId.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes
import com.jhtest.storibanktest.ui.theme.components.utils.shimmerEffect

@Composable
internal fun FaceIdShimmer(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer),
    ) {
        val (header, photoSection, signUpBtn) = createRefs()

        Column(
            modifier =
                modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .padding(horizontal = 24.dp)
                    .constrainAs(header) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
        ) {
            Spacer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(Color.Transparent, Shapes.medium)
                        .shimmerEffect(radius = 8.dp),
            )

            Spacer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .padding(top = 32.dp, start = 24.dp, end = 24.dp)
                        .background(Color.Transparent, Shapes.medium)
                        .shimmerEffect(radius = 8.dp),
            )
        }

        Spacer(
            modifier =
                Modifier
                    .constrainAs(photoSection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(300.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp),
        )

        Spacer(
            modifier =
                Modifier
                    .constrainAs(signUpBtn) {
                        bottom.linkTo(parent.bottom, 54.dp)
                        start.linkTo(parent.start, 24.dp)
                        end.linkTo(parent.end, 24.dp)
                    }
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp)
                    .background(Color.Transparent, Shapes.medium)
                    .shimmerEffect(radius = 8.dp),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FaceIdShimmerPreview() {
    FaceIdShimmer()
}
