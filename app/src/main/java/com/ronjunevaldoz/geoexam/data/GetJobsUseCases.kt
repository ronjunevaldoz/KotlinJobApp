package com.ronjunevaldoz.geoexam.data

import com.ronjunevaldoz.geoexam.ui.viewstate.JobViewState
import com.ronjunevaldoz.geoexam.util.AppJson
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetJobsUseCases @Inject constructor(
    private val repository: JobRepository
) {
    fun getAll() = flow {
        emit(JobViewState.Loading)
        try {
            val json = repository.getJobs().string()
            emit(JobViewState.Success(AppJson.decodeFromString(json)))
        } catch (e: Exception) {
            emit(JobViewState.Failure(e))
        }
    }
}