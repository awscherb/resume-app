package com.awscherb.resume.ui.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awscherb.resume.data.Project
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography

@Composable
fun ProjectDetail(
    project: Project
) {
    Column(
        modifier = Modifier.padding(
            vertical = 8.dp, horizontal = 16.dp
        )
    ) {
        Text(
            project.name,
            style = Typography.headlineSmall,
        )

        Text(
            text = project.shortDescription,
            style = Typography.titleSmall
        )

        val uriHandler = LocalUriHandler.current
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable {
                    uriHandler.openUri(project.mainLink)
                },
            textDecoration = TextDecoration.Underline,
            text = project.mainLink,
            fontWeight = FontWeight.Light
        )

        project.secondaryLink?.let { secondary ->
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {
                        uriHandler.openUri(secondary)
                    },
                textDecoration = TextDecoration.Underline,
                text = secondary,
                fontWeight = FontWeight.Light
            )
        }

        project.longDescription?.let { longDesc ->
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = longDesc
            )
        }
    }

}

@Composable
@Preview(showSystemUi = true, apiLevel = 33)
fun ProjectPreview() {
    Resume3Theme {
        ProjectDetail(
            project = Project(
                "Project",
                "Short Description",
                "Long Description",
                "http://www.primary.com",
                "http://www.secondary.com",
                0
            )
        )
    }
}