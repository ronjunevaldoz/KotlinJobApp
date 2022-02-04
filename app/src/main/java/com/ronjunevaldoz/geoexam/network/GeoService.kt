package com.ronjunevaldoz.geoexam.network

import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *  Geo service api
 */
interface GeoService {
    /**
     * get oauth token
     *  @return temporary use ResponseBody due to converter bug issue
     */
    @POST("oauth/token")
    @FormUrlEncoded
    suspend fun getOAuthToken(
        @Field("grant_type") grantType: String,
        @Field("login") login: String,
        @Field("password") password: String
    ): ResponseBody

    /**
     *  get all jobs
     *
     *  @return temporary use ResponseBody due to converter bug issue
     */
    @GET("api/v1/job/actual")
    suspend fun getJobs(): ResponseBody

    /**
     * create a job
     * @return temporary use ResponseBody due to converter bug issue
     */
    @POST("api/v1/job/actual")
    @FormUrlEncoded
    suspend fun createJob(
        @Field("title") title: String,
        @Field("description") description: String
    ): ResponseBody
//    @POST("api/v1/job/actual")
//    @FormUrlEncoded
//    suspend fun createJob(
//        @Field("title") title: String,
//        @Field("description") description: String
//    ) : Flow<ActualJob>
    /**
     * get job status
     */
    @POST("api/v1/job_status")
    suspend fun getJobStatus()

    /**
     * get client
     */
    @GET("api/v1/client")
    suspend fun getClient()
}