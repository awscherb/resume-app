@file:OptIn(ExperimentalMaterial3Api::class)

package com.awscherb.resume.ui.resume

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.awscherb.resume.data.LoadState
import com.awscherb.resume.data.Resume
import com.awscherb.resume.ui.common.ErrorScreen
import com.awscherb.resume.ui.common.ScaffoldScreen
import com.awscherb.resume.ui.theme.LoadingScreen

@Composable
fun HomeScreen(
    loadState: LoadState<Resume>,
    navOnClick: () -> Unit
) {
    ScaffoldScreen(title = "Resume", navOnClick = navOnClick) { padding ->
        when (loadState) {
            is LoadState.Error -> {
                ErrorScreen(padding, loadState.error)
            }

            is LoadState.Loading<Resume> -> {
                LoadingScreen(padding)
            }

            is LoadState.Success -> {
                ResumeScreen(padding, resume = loadState.data)
            }
        }
    }

}