package com.awscherb.resume.ui

import androidx.lifecycle.ViewModel
import com.awscherb.resume.data.Resume
import com.awscherb.resume.data.ResumeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Optional
import javax.inject.Inject

@HiltViewModel
class ResumeViewModel @Inject constructor(
    resumeRepository: ResumeRepository
) : ViewModel() {

    val resume = resumeRepository.fetchResume()

}