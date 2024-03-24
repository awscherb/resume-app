package com.awscherb.resume.ui.projects

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.awscherb.resume.data.LoadState
import com.awscherb.resume.data.Loading
import com.awscherb.resume.data.Project
import com.awscherb.resume.data.Projects
import com.awscherb.resume.data.Resume
import com.awscherb.resume.ui.common.ErrorScreen
import com.awscherb.resume.ui.common.ScaffoldScreen
import com.awscherb.resume.ui.resume.ResumeScreen
import com.awscherb.resume.ui.theme.LoadingScreen
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography
import java.util.Date

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = hiltViewModel(),
    navOnClick: () -> Unit
) {
    val loadState by viewModel.projects.collectAsState(initial = Loading())
    ScaffoldScreen(title = "Projects", navOnClick = navOnClick) { padding ->
        when (loadState) {
            is LoadState.Error -> {
                ErrorScreen(padding, (loadState as LoadState.Error<Projects>).error)
            }

            is LoadState.Loading<Projects> -> {
                LoadingScreen(padding)
            }

            is LoadState.Success -> {
                ProjectScreenContent(
                    padding,
                    projects = (loadState as LoadState.Success<Projects>).data
                )
            }
        }
    }
}

@Composable
fun ProjectScreenContent(
    paddingValues: PaddingValues,
    projects: Projects
) {

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            Text(
                text = "Projects",
                modifier = Modifier
                    .padding(
                        bottom = 8.dp,
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                style = Typography.displayMedium
            )
        }

        itemsIndexed(projects.projects) { index, item ->
            ProjectDetail(project = item)
            if (index < projects.projects.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Preview(apiLevel = 33, showSystemUi = true)
@Composable
fun ProjectScreenContentPreview() {
    Resume3Theme {
        ProjectScreenContent(
            paddingValues = PaddingValues(), projects =
            Projects(
                1, Date(),
                listOf(
                    Project(
                        "Project",
                        "Short Description",
                        "Long Description",
                        "http://www.com",
                        null,
                        0
                    )
                )
            )
        )
    }
}