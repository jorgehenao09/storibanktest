package com.jhtest.storibanktest.ui.theme.components.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp

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

fun Modifier.formModifier() =
    this
        .padding(top = 16.dp)
        .padding(horizontal = 24.dp)