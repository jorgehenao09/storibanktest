package com.jhtest.storibanktest.utils

import android.content.Context
import java.io.File

fun Context.createFile() = File(
    externalMediaDirs.firstOrNull(),
    "${System.currentTimeMillis()}.jpg"
)