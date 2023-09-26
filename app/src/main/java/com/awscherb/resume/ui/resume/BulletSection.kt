package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awscherb.resume.data.Bullet
import com.awscherb.resume.ui.theme.Resume3Theme

@Composable
fun BulletSection(bullets: List<Bullet>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        bullets.forEach { bullet ->
            Row {
                Text(text = "- ")
                Text(
                    text = bullet.text,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    fontFamily = FontFamily.Serif,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun BulletsPreview() {
    Resume3Theme {
        BulletSection(
            bullets =
            listOf(
                Bullet(
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                    0,
                )
            )
        )
    }
}