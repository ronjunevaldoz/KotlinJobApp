package com.ronjunevaldoz.geoexam.network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OAuthTokenResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Int,
    @SerialName("refresh_token")
    val refreshToken: String,
    val scope: String,
    @SerialName("created_at")
    val createdAt: Long
)

@Serializable
data class Company(
    val id: Int,
    val name: String,
    val country: String,
    val timezone: String,
    val mailing_address: String?,
    val physical_address: String?,
    val subscription_status: String,
    val price_plan: String
)

@Serializable
data class ActualJob(
    val id: Int,
    val id_uuid: String,
    val title: String,
    val description: String?,
    val reference: String = "",
    @SerialName("order_number")
    val orderNumber: String?,
    val type : String,
    val billed_client : String?,
    val address: JobAddress?,
    val job_status : JobStatus?,
    val priority: Int = 1,
//    @SerialName("job_status_id")
//    val jobStatusId: Int,
//    @SerialName("client_id")
//    val clientId: Int,
    val clients : List<Client>,
    val entity_permissions : EntityPermission
)

@Serializable
data class Client(
    val job_share : Boolean,
    val id: Int,
    val id_uuid: String,
    val company_name:String,
    val first_name: String,
    val last_name: String
)

@Serializable
data class JobStatus(
    val id : Int,
    val color: String?,
    val color_hex: String,
    val name: String,
    val sort_order: Int,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("created_at")
    val createdAt: String,
    val status_group : Int,
    val deleted: Boolean = false
)

@Serializable
data class EntityPermission(
    val update: Boolean,
    val delete: Boolean,
    val archive: Boolean,
)

@Serializable
data class JobAddress(
    val id: String = "",
    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val country: String = "",
    @SerialName("postcode")
    val postCode: String,
    val longitude: String = "",
    val latitude: String = "",
    val active: Boolean = false,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("created_at")
    val createdAt: String
)
