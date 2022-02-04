package com.ronjunevaldoz.geoexam.data

import com.ronjunevaldoz.geoexam.network.data.OAuthTokenResponse
import com.ronjunevaldoz.geoexam.ui.viewstate.LoginViewState
import com.ronjunevaldoz.geoexam.util.AppJson
import com.ronjunevaldoz.geoexam.util.DataStoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCases @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val repository: JobRepository
) {

    fun login(email: String, password: String) = flow {
        emit(LoginViewState.Loading)
        delay(1000)
        try {
            val body = repository.login(email, password)
            val result = AppJson.decodeFromString<OAuthTokenResponse>(body.string())
            dataStoreManager.save("access_token", result.accessToken)
            emit(LoginViewState.Success(result))
        } catch (e: HttpException) {
            emit(LoginViewState.Failure(e))
        }
    }
}