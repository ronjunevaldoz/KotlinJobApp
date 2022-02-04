package com.ronjunevaldoz.geoexam.ui.component.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ronjunevaldoz.geoexam.common.Screen
import com.ronjunevaldoz.geoexam.ui.viewmodel.JobViewModel
import com.ronjunevaldoz.geoexam.ui.viewstate.JobViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JobListing(navController: NavController, jobViewModel: JobViewModel) {
    val jobViewState = jobViewModel.jobViewState.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column {
            JobTopAppBar(
                onAddJob = {
                    navController.navigate(Screen.JobCreation.route)
                }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when (val jobState = jobViewState.value) {
                    is JobViewState.Loading -> {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                    is JobViewState.Empty -> {
                        Text(text = "No jobs available.", Modifier.align(Alignment.Center))
                    }
                    is JobViewState.Success -> {
                        LazyColumn(
                            Modifier.padding(8.dp)
                        ) {
                            items(jobState.data) { job ->
                                JobItem(job)
                                Spacer(modifier = Modifier.height(6.dp))
                            }
                        }
                    }
                    is JobViewState.Failure -> {
                        Text(text = "Fail: ${jobState.e.message}")
                    }
                }
            }
        }
    }
}