package com.awscherb.resume.ui.projects

import androidx.lifecycle.ViewModel
import com.awscherb.resume.data.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    projectRepository: ProjectRepository
) : ViewModel() {

    val projects = projectRepository.fetchProjects()
}