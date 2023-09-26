package com.awscherb.resume.ui.resume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.awscherb.resume.data.Role
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun RolesSection(roles: List<Role>) {
    Column {
        roles.forEach { role ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val style =
                    if (role.latest || roles.size == 1) Typography.bodySmall.copy(
                        fontSize = 13.sp
                    )
                    else Typography.bodySmall

                Text(
                    text = role.title.uppercase(),
                    style = style
                )

                val startText = "${
                    Month.of(role.startMonth).getDisplayName(
                        TextStyle.SHORT, Locale.getDefault()
                    )
                } ${role.startYear}"

                val endText =
                    if (role.endMonth != null) {
                        "${
                            Month.of(role.endMonth).getDisplayName(
                                TextStyle.SHORT, Locale.getDefault()
                            )
                        } ${role.endYear}"
                    } else {
                        "Present"
                    }

                Text(
                    text = "$startText - $endText".uppercase(),
                    style = style
                )

            }
        }
    }
}

@Preview
@Composable
fun RolesPreview() {
    Resume3Theme {
        RolesSection(
            roles = listOf(
                Role(
                    title = "Senior Android Engineer",
                    startMonth = 9,
                    startYear = 2021,
                    endMonth = null,
                    endYear = null,
                    latest = true
                ),
                Role(
                    title = "Android Engineer",
                    startMonth = 6,
                    startYear = 2020,
                    endMonth = 9,
                    endYear = 2021,
                    latest = false
                ),
            )
        )
    }
}
