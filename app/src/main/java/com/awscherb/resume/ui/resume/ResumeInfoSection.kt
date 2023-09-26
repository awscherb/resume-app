package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awscherb.resume.ui.theme.Resume3Theme
import java.util.Date

@Composable
fun ResumeInfo(version: Int, date: Long) {
    Column(Modifier.padding(vertical = 24.dp)) {
        Text(
            text = "Resume version $version",
            fontStyle = FontStyle.Italic
        )
        Text(text = "Published: ${Date(date * 1000)}",
            fontStyle = FontStyle.Italic)
    }
}

@Preview
@Composable
fun ResumeInfoPreview() {
    Resume3Theme {
        ResumeInfo(version = 5, date = System.currentTimeMillis())
    }
}