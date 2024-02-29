package com.awscherb.resume.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val label: String,
    val path: String,
    val icon: ImageVector
) {

    data object Resume : Destination(
        label = "Resume",
        path = "resume",
        icon = Icons.Default.Home
    )

    data object Projects : Destination(
        label = "Projects",
        path = "projects",
        icon = Icons.Default.Build
    )

    data object About : Destination(
        label = "About",
        path = "About",
        icon = Icons.Default.Info
    )
}