package com.awscherb.resume.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val label: String,
    val path: String,
    val icon: ImageVector
) {

    object Resume : Destination(
        label = "Resume",
        path = "resume",
        icon = Icons.Default.Home
    )

    object About : Destination(
        label = "About",
        path = "About",
        icon = Icons.Default.Info
    )
}