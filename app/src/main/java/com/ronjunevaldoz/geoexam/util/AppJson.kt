package com.ronjunevaldoz.geoexam.util

import kotlinx.serialization.json.Json

val AppJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true // to include default value in encoding
    coerceInputValues = true
    classDiscriminator = "#class" // to avoid type as default reserve discriminator
}