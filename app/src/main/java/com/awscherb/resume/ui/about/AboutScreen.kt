@file:OptIn(ExperimentalMaterial3Api::class)

package com.awscherb.resume.ui.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.awscherb.resume.ui.common.ScaffoldScreen
import com.awscherb.resume.ui.theme.Typography
import com.awscherb.resume.ui.util.AppVersion
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun AboutScreen(
    appVersion: AppVersion?,
    navOnClick: () -> Unit
) {
    val context = LocalContext.current
    ScaffoldScreen(title = "About", navOnClick = navOnClick) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            item {
                TextButton(
                    modifier = Modifier.padding(top = 24.dp),
                    onClick = {
                        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
                    }) {
                    Text(
                        text = "Open Source Licenses",
                        style = Typography.titleLarge
                    )
                }
            }

            item {
                TextButton(
                    modifier = Modifier.padding(bottom = 24.dp),
                    onClick = {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://github.com/awscherb/resume-app")
                            )
                        )
                    }) {
                    Text(
                        text = "GitHub Repository",
                        style = Typography.titleLarge
                    )
                }
            }

            item {
                Divider()
            }

            item {
                Text(
                    text = "App version ${appVersion?.versionName} (${appVersion?.versionNumber})",
                    modifier = Modifier.padding(top = 24.dp),
                    style = Typography.bodyMedium
                )
            }
        }
    }
}