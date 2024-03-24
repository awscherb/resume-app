package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.awscherb.resume.data.LoadState
import com.awscherb.resume.data.Loading
import com.awscherb.resume.data.Resume
import com.awscherb.resume.ui.ResumeViewModel
import com.awscherb.resume.ui.common.ErrorScreen
import com.awscherb.resume.ui.common.ScaffoldScreen
import com.awscherb.resume.ui.theme.LoadingScreen


@Composable
fun ResumeScreen(
    viewModel: ResumeViewModel = hiltViewModel(),
    navOnClick: () -> Unit
) {

    val resumeState by viewModel.resume.collectAsState(initial = Loading())
    ScaffoldScreen(title = "Resume", navOnClick = navOnClick) { padding ->
        when (resumeState) {
            is LoadState.Error -> {
                ErrorScreen(padding, (resumeState as LoadState.Error<Resume>).error)
            }

            is LoadState.Loading<Resume> -> {
                LoadingScreen(padding)
            }

            is LoadState.Success -> {
                ResumeScreenInner(padding, resume = (resumeState as LoadState.Success<Resume>).data)
            }
        }
    }

}

@Composable
fun ResumeScreenInner(paddingValues: PaddingValues, resume: Resume) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            ResumeHeader(
                name = resume.name, email =
                resume.email,
                github = resume.github
            )
        }

        item { HorizontalDivider() }
        item {
            SectionHeader(text = "Work Experience")
        }

        resume.jobs.forEach {
            item {
                JobHeader(job = it)
            }
            item {
                RolesSection(roles = it.roles)
            }
            item {
                BulletSection(bullets = it.bullets)
            }
        }
        item { HorizontalDivider() }

        item {
            SectionHeader(
                text = "Education"
            )
        }


        item {
            EducationSection(resume.education)
        }

        item { HorizontalDivider() }

        item {
            ResumeInfo(version = resume.version, date = resume.versionDate)
        }

    }
}
