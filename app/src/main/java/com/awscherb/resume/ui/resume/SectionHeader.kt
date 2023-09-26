package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.awscherb.resume.ui.theme.Typography

@Composable
fun SectionHeader(text: String) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = text.uppercase(),
        style = Typography.labelMedium
    )
}