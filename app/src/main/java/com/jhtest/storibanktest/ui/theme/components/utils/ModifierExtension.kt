package com.jhtest.storibanktest.ui.theme.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.clickableNoRipple(onClickItem: () -> Unit) =
    composed(
        factory = {
            Modifier.clickable(
                indication = null,
                interactionSource =
                remember {
                    MutableInteractionSource()
                }
            ) {
                onClickItem()
            }
        }
    )