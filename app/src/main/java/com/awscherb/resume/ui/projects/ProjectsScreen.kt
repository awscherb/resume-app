package com.awscherb.resume.ui.projects

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.awscherb.resume.data.LoadState
import com.awscherb.resume.data.Projects
import com.awscherb.resume.data.Resume
import com.awscherb.resume.ui.common.ErrorScreen
import com.awscherb.resume.ui.common.ScaffoldScreen
import com.awscherb.resume.ui.resume.ResumeScreen
import com.awscherb.resume.ui.theme.LoadingScreen

@Composable
fun ProjectsScreen(
    loadState: LoadState<Projects>,
    navOnClick: () -> Unit
) {
    ScaffoldScreen(title = "Projects", navOnClick = navOnClick) { padding ->
        when (loadState) {
            is LoadState.Error -> {
                ErrorScreen(padding, loadState.error)
            }

            is LoadState.Loading<Projects> -> {
                LoadingScreen(padding)
            }

            is LoadState.Success -> {
                ProjectScreenContent(padding, projects = loadState.data)
            }
        }
    }
}

@Composable
fun ProjectScreenContent(
    paddingValues: PaddingValues,
    projects: Projects
) {

}