package com.ronjunevaldoz.geoexam.ui.viewstate

import com.ronjunevaldoz.geoexam.network.data.ActualJob

sealed class JobViewState {
    object Empty : JobViewState()
    object Loading : JobViewState()
    data class Success(val data: List<ActualJob>) : JobViewState()
    data class Failure(val e: Throwable) : JobViewState()
}