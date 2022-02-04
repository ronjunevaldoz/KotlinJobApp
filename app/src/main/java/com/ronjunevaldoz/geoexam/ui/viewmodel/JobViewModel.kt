package com.ronjunevaldoz.geoexam.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ronjunevaldoz.geoexam.data.GetJobsUseCases
import com.ronjunevaldoz.geoexam.ui.viewstate.JobViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JobViewModel @Inject constructor(
    application: Application,
    private val getJobsUseCases: GetJobsUseCases
) : AndroidViewModel(application) {

    private val _jobViewState = MutableStateFlow<JobViewState>(JobViewState.Empty)
    val jobViewState = _jobViewState


    init {
        loadJobs()
    }

      fun loadJobs() {
        viewModelScope.launch {
            getJobsUseCases.getAll().collectLatest {
                _jobViewState.value = it
            }
        }
    }
}