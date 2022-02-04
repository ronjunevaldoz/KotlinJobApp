package com.ronjunevaldoz.geoexam.ui.component.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ronjunevaldoz.geoexam.ui.viewmodel.CreateJobViewModel
import com.ronjunevaldoz.geoexam.ui.viewstate.JobCreateViewState
import kotlinx.coroutines.launch


@Composable
fun JobForm(navController: NavController) {
    val createJobViewModel = hiltViewModel<CreateJobViewModel>()
    val createJobViewState by createJobViewModel.createJobViewState.collectAsState()

    val titleValue = remember { mutableStateOf("") }
    val descValue = remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column {
            JobFormTopAppBar(
                onCreateDone = {
                    createJobViewModel.createJob(
                        title = titleValue.value,
                        desc = descValue.value
                    )
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    OutlinedTextField(
                        value = titleValue.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onValueChange = { titleValue.value = it },
                        placeholder = { Text("Enter Title") },
                        label = { Text("Enter Title") }
                    )
                    OutlinedTextField(
                        value = descValue.value,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onValueChange = { descValue.value = it },
                        placeholder = { Text("Enter Description") },
                        label = { Text("Enter Description") }
                    )

                    when (val state = createJobViewState) {
                        is JobCreateViewState.Empty -> {}
                        is JobCreateViewState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                        is JobCreateViewState.Success -> {
                            titleValue.value = ""
                            descValue.value = ""
                            coroutineScope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    "Job creation successful! Moving back to job listing"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    navController.popBackStack()
                                }
                                navController.popBackStack()
                            }
                        }
                        is JobCreateViewState.Failure -> {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Job creation failed! ${state.e.message}"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}