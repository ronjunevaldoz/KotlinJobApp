package com.ronjunevaldoz.geoexam.ui.viewstate

import com.ronjunevaldoz.geoexam.network.data.OAuthTokenResponse

sealed class LoginViewState {
    object Empty : LoginViewState()
    object Loading : LoginViewState()
    data class Success(val data: OAuthTokenResponse) : LoginViewState()
    data class Failure(val e: Throwable) : LoginViewState()
}