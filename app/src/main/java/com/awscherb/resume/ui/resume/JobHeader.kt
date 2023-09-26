package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awscherb.resume.data.Job
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography

@Composable
fun JobHeader(job: Job) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            job.company,
            style = Typography.headlineSmall,
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = " | ",
            fontSize = 12.sp,
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = job.location,
            style = Typography.labelLarge.copy(fontWeight = FontWeight.Normal),
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JobHeaderPreview() {
    Resume3Theme {
        JobHeader(
            job = Job(
                order = 0,
                company = "Peloton",
                url = "www.com",
                location = "New York, NY",
                startMonth = 8,
                startYear = 2032,
                latest = true,
                endMonth = null,
                endYear = null,
                bullets = emptyList(),
                roles = emptyList()
            )
        )
    }
}
