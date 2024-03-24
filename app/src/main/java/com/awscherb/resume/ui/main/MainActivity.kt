package com.awscherb.resume.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.awscherb.resume.ui.about.AboutScreen
import com.awscherb.resume.ui.projects.ProjectsScreen
import com.awscherb.resume.ui.resume.ResumeScreen
import com.awscherb.resume.ui.theme.Resume3Theme
import com.awscherb.resume.ui.util.getAppVersion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val startDestination = Destination.Resume
            Resume3Theme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                var currentDestination by remember {
                    mutableStateOf<Destination>(startDestination)
                }

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            NavDrawerContent(
                                selectedItem = currentDestination,
                                topLevelNav = {
                                    currentDestination = it
                                    navController.popBackStack()
                                    navController.navigate(it.path)
                                    scope.launch { drawerState.close() }
                                }
                            )
                        }
                    }) {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination.path
                    ) {
                        composable(Destination.Resume.path) {
                            ResumeScreen {
                                scope.launch { drawerState.open() }
                            }
                        }
                        composable(Destination.Projects.path) {
                            ProjectsScreen {
                                scope.launch { drawerState.open() }
                            }
                        }
                        composable(Destination.About.path) {
                            AboutScreen(getAppVersion(this@MainActivity)) {
                                scope.launch { drawerState.open() }
                            }
                        }
                    }
                }
            }
        }
    }
}