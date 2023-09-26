package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awscherb.resume.data.Education
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography

@Composable
fun EducationSection(education: List<Education>) {
    Column(
        modifier = Modifier.padding(
            top = 8.dp,
            bottom = 24.dp
        )
    ) {
        education.forEach { education ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = education.institution,
                        style = Typography.headlineSmall,
                    )

                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = "${education.resultYear}",
                        style = Typography.bodySmall.copy(
                            fontSize = 13.sp
                        )
                    )

                }
                education.subinstitution?.let {
                    Text(
                        text = education.subinstitution,
                        style = Typography.bodySmall.copy(
                            fontSize = 13.sp
                        )
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = education.result,
                    style = Typography.bodyLarge
                )
            }
        }
    }
}

@Composable
@Preview
fun EducationPreview() {
    Resume3Theme {
        EducationSection(
            education = listOf(
                Education(
                    institution = "Northeastern University",
                    subinstitution = "College of Comptuer and Information Science",
                    result = "Bachelor of Science, Computer Science",
                    resultYear = 2017
                )
            )
        )
    }
}