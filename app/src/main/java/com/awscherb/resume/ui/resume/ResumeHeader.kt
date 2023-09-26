package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography

@Composable
fun ResumeHeader(
    name: String,
    email: String,
    github: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 16.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(bottom = 8.dp),
            style = Typography.displayMedium
        )

        Text(
            text = email,
            fontWeight = FontWeight.Light
        )

        Text(
            text = "github.com/${github}",
            fontWeight = FontWeight.Light
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResumeHeaderPreview() {
    Resume3Theme {
        ResumeHeader(name = "Alex Scherb", email = "email@gmail.com", github = "awscherb")
    }
}