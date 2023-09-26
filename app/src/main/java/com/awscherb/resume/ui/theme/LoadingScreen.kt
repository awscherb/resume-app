package com.awscherb.resume.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight(.15F)
                .fillMaxWidth(.15F)
        )

        Text(
            text = "Loading"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoadingScreenPreview() {
    Row(
        modifier = Modifier.fillMaxHeight()
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxHeight(.15F)
                .fillMaxWidth(.15F)
        )
    }
}