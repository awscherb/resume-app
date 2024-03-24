package com.awscherb.resume.ui.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.theme.Typography

@Composable
fun NavDrawerContent(
    selectedItem: Destination,
    topLevelNav: (Destination) -> Unit
) {
    Text(
        text = "Resume",
        modifier = Modifier.padding(
            top = 36.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = 24.dp
        ),
        style = Typography.headlineLarge
    )
    HorizontalDivider()
    NavDrawerRow(
        selectedItem = selectedItem,
        destination = Destination.Resume,
        topLevelNav = topLevelNav
    )
    NavDrawerRow(
        selectedItem = selectedItem,
        destination = Destination.Projects,
        topLevelNav = topLevelNav
    )
    HorizontalDivider()

    NavDrawerRow(
        selectedItem = selectedItem,
        destination = Destination.About,
        topLevelNav = topLevelNav
    )
}

@Composable
fun NavDrawerRow(
    selectedItem: Destination,
    destination: Destination,
    topLevelNav: (Destination) -> Unit
) {
    NavigationDrawerItem(
        label = {
            Row {
                Icon(destination.icon, destination.label)
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically),
                    text = destination.label
                )
            }
        },
        selected = selectedItem == destination,
        onClick = {
            topLevelNav(destination)
        })
}

@Composable
@Preview
fun NavDrawerRowPreview() {
    Resume3Theme {
        NavDrawerRow(
            selectedItem = Destination.About,
            destination = Destination.About,
            topLevelNav = {})
    }
}

@Composable
@Preview
fun NavDrawerContentPreview() {
    Resume3Theme {
        ModalDrawerSheet {
            NavDrawerContent(selectedItem = Destination.About, topLevelNav = {})
        }
    }
}