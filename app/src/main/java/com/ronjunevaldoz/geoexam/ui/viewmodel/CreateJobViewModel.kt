package com.ronjunevaldoz.geoexam.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ronjunevaldoz.geoexam.data.CreateJobUseCases
import com.ronjunevaldoz.geoexam.ui.viewstate.JobCreateViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateJobViewModel @Inject constructor(
    application: Application ,
    private val createJobUseCases: CreateJobUseCases
) : AndroidViewModel(application) {

    private val _createJobViewState = MutableStateFlow<JobCreateViewState>(JobCreateViewState.Empty)
    val createJobViewState = _createJobViewState

    fun createJob(title: String, desc: String) {
        viewModelScope.launch {
            createJobUseCases.create(
                title = title,
                desc = desc
            ).collectLatest {
                _createJobViewState.value = it
            }
        }
    }
}