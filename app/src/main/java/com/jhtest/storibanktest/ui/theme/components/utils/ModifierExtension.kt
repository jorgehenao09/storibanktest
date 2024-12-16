package com.jhtest.storibanktest.ui.theme.components.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.ui.theme.ShimmerColor
import com.jhtest.storibanktest.ui.theme.ShimmerColorBg

fun Modifier.clickableNoRipple(onClickItem: () -> Unit) =
    composed(
        factory = {
            Modifier.clickable(
                indication = null,
                interactionSource =
                    remember {
                        MutableInteractionSource()
                    },
            ) {
                onClickItem()
            }
        },
    )

fun Modifier.formModifier() =
    this
        .padding(top = 16.dp)
        .padding(horizontal = 24.dp)

fun Modifier.shimmerEffect(
    topStart: Dp = 0.dp,
    topEnd: Dp = 0.dp,
    bottomEnd: Dp = 0.dp,
    bottomStart: Dp = 0.dp,
    radius: Dp = 0.dp,
): Modifier =
    composed {
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition(label = "")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec =
                infiniteRepeatable(
                    animation = tween(700),
                ),
            label = "",
        )
        val cornerRadius =
            if (radius.value != 0f) {
                RoundedCornerShape(radius)
            } else {
                RoundedCornerShape(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = bottomEnd,
                    bottomStart = bottomStart,
                )
            }

        background(
            shape = cornerRadius,
            brush =
                Brush.linearGradient(
                    colors =
                        listOf(
                            ShimmerColorBg,
                            ShimmerColor,
                            ShimmerColorBg,
                        ),
                    start = Offset(startOffsetX, 0f),
                    end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat()),
                ),
        ).onGloballyPositioned {
            size = it.size
        }
    }
