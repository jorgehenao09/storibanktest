package com.jhtest.storibanktest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jhtest.storibanktest.R

val Rubik =
    FontFamily(
        Font(R.font.rubik_regular),
        Font(R.font.rubik_bold, FontWeight.Bold),
    )

// Set of Material typography styles to start with
val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.0.sp,
                letterSpacing = 0.4.sp,
            ),
        labelMedium =
            TextStyle(
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 16.0.sp,
                letterSpacing = 0.4.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 16.0.sp,
                letterSpacing = 0.5.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                lineHeight = 36.0.sp,
                letterSpacing = 0.0.sp,
            ),
        titleLarge =
            TextStyle(
                fontFamily = Rubik,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp,
            ),
    )
