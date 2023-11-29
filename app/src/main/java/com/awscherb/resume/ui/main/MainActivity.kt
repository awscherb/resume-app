package com.awscherb.resume.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.awscherb.resume.data.Loading
import com.awscherb.resume.ui.ResumeViewModel
import com.awscherb.resume.ui.about.AboutScreen
import com.awscherb.resume.ui.resume.HomeScreen
import com.awscherb.resume.ui.theme.Typography
import com.awscherb.resume.ui.util.getAppVersion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            val vm = hiltViewModel<ResumeViewModel>()
            val resumeState by vm.resume.collectAsState(initial = Loading())
            var currentDestination by remember {
                mutableStateOf("resume")
            }

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
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
                        Divider()
                        NavigationDrawerItem(
                            label = { Text("Resume") },
                            selected = currentDestination == "resume",
                            icon = {
                                Icon(Icons.Default.Home, contentDescription = "Resume")
                            },
                            onClick = {
                                navController.navigate("resume") {
                                    popUpTo("about") {
                                        inclusive = true
                                    }
                                }
                                currentDestination = "resume"
                                scope.launch {
                                    drawerState.close()
                                }
                            })
                        NavigationDrawerItem(
                            label = { Text("About") },
                            selected = currentDestination == "about",
                            icon = {
                                Icon(Icons.Default.Info, contentDescription = "About")
                            },
                            onClick = {
                                currentDestination = "about"
                                navController.navigate("about") {
                                    popUpTo("resume") {
                                        inclusive = true
                                    }
                                }
                                scope.launch {
                                    drawerState.close()
                                }
                            })
                    }
                }) {
                NavHost(navController = navController, startDestination = "resume") {
                    composable("resume") {
                        HomeScreen(loadState = resumeState) {
                            scope.launch { drawerState.open() }
                        }
                    }
                    composable("about") {
                        AboutScreen(getAppVersion(this@MainActivity)) {
                            scope.launch { drawerState.open() }
                        }
                    }
                }
            }
        }
    }
}