package com.ronjunevaldoz.geoexam.data

import com.ronjunevaldoz.geoexam.network.GeoService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class JobRepository @Inject constructor(
    private val geoService: GeoService
) {
    suspend fun login(email: String, password: String) = geoService.getOAuthToken(
        grantType = "password",
        login = email,
        password = password
    )

    suspend fun createJob(title: String, desc: String) = geoService.createJob(title, desc)
    suspend fun getJobs() = geoService.getJobs()
    suspend fun getClients() = geoService.getClient()
    suspend fun getStatuses() = geoService.getJobStatus()
}