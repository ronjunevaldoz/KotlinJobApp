package com.ronjunevaldoz.geoexam.data

import com.ronjunevaldoz.geoexam.ui.viewstate.JobCreateViewState
import com.ronjunevaldoz.geoexam.util.AppJson
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateJobUseCases @Inject constructor(
    private val repository: JobRepository
) {
    fun create(title: String, desc: String) = flow {
        emit(JobCreateViewState.Loading)
        try {
            val json = repository.createJob(title, desc).string()
            emit(JobCreateViewState.Success(AppJson.decodeFromString(json)))
        } catch (e: Exception) {
            emit(JobCreateViewState.Failure(e))
        }
    }
}