package com.ronjunevaldoz.geoexam.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ronjunevaldoz.geoexam.common.Screen
import com.ronjunevaldoz.geoexam.ui.component.screen.JobForm
import com.ronjunevaldoz.geoexam.ui.component.screen.JobListing
import com.ronjunevaldoz.geoexam.ui.component.screen.LoginScreen
import com.ronjunevaldoz.geoexam.ui.component.screen.SplashScreen
import com.ronjunevaldoz.geoexam.ui.viewmodel.JobViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            BackHandler { }
            LoginScreen(navController)
        }
        composable(Screen.JobListing.route) {
            BackHandler { }
            val jobViewModel = hiltViewModel<JobViewModel>()
            jobViewModel.loadJobs()
            JobListing(navController, jobViewModel)
        }
        composable(Screen.JobCreation.route) {
            JobForm(navController)
        }
    }
}