package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.awscherb.resume.data.Resume

@Composable
fun ResumeScreen(paddingValues: PaddingValues, resume: Resume) {
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

        item { Divider() }
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
        item { Divider() }

        item {
            SectionHeader(
                text = "Education"
            )
        }


        item {
            EducationSection(resume.education)
        }

        item { Divider() }

        item {
            ResumeInfo(version = resume.version, date = resume.versionDate)
        }

    }
}
