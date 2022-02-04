package com.ronjunevaldoz.geoexam.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ronjunevaldoz.geoexam.BuildConfig
import com.ronjunevaldoz.geoexam.network.AccessTokenInterceptor
import com.ronjunevaldoz.geoexam.network.GeoService
import com.ronjunevaldoz.geoexam.util.AppJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptorOkHttpClient

/**
 *  Network modules
 *
 *  @author Ron June Valdoz
 */
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val GeoBaseUrl = "https://geoxqa-api.geo.ventures"
    private val ContentType = "application/json".toMediaType()

    @Singleton
    @Provides
    fun provideJsonConverterFactory() = AppJson.asConverterFactory(ContentType)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor()

    @LoggingInterceptorOkHttpClient
    @Provides
    fun provideLoggingInterceptorOkHttpClient(
        authInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(
        authInterceptor: AccessTokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGeoService(
        jsonConverterFactory: Converter.Factory,
        @LoggingInterceptorOkHttpClient loggingClient: OkHttpClient,
        @AuthInterceptorOkHttpClient authClient: OkHttpClient,
    ): GeoService {
        val builder = Retrofit.Builder().baseUrl(GeoBaseUrl)
        if (BuildConfig.DEBUG) {
            builder.apply {
                client(loggingClient)
            }
        }
        return builder
            .client(authClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
            .create(GeoService::class.java)
    }
}