package com.ronjunevaldoz.geoexam.network

import com.ronjunevaldoz.geoexam.util.DataStoreManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenInterceptor @Inject constructor(private val dataStoreManager: DataStoreManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { dataStoreManager.get("access_token").first() }
        if (accessToken.isEmpty()) {
            return chain.proceed(chain.request())
        }
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(newRequest)
    }
}