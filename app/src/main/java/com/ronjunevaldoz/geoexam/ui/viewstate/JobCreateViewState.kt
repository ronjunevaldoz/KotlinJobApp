package com.ronjunevaldoz.geoexam.ui.viewstate

import com.ronjunevaldoz.geoexam.network.data.ActualJob

sealed class JobCreateViewState {
    object Empty : JobCreateViewState()
    object Loading : JobCreateViewState()
    data class Success(val data: ActualJob) : JobCreateViewState()
    data class Failure(val e: Throwable) : JobCreateViewState()
}