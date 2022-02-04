package com.ronjunevaldoz.geoexam.common

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object JobListing : Screen("job_listing")
    object JobCreation : Screen("job_creation")
}