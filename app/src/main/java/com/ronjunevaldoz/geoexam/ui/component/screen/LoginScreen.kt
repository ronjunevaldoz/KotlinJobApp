package com.ronjunevaldoz.geoexam.ui.component.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ronjunevaldoz.geoexam.common.Screen
import com.ronjunevaldoz.geoexam.ui.viewmodel.LoginViewModel
import com.ronjunevaldoz.geoexam.ui.viewstate.LoginViewState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val loginViewState by loginViewModel.loginViewState.collectAsState(LoginViewState.Empty)
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val emailTextValue = remember { mutableStateOf("ronjune.lopez@gmail.com") }
    val passwordTextValue = remember { mutableStateOf("Password1") }
    val emailError = remember { mutableStateOf(false) }
    val passwordError = remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                OutlinedTextField(
                    value = emailTextValue.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = {
                        emailError.value = it.isEmpty()
                        emailTextValue.value = it
                        loginViewModel.clearState()
                    },
                    placeholder = { Text("Enter Password") },
                    label = { Text("Enter Password") },
                    isError = emailError.value
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = passwordTextValue.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {
                        passwordError.value = it.isEmpty()
                        passwordTextValue.value = it
                        loginViewModel.clearState()
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    placeholder = { Text("Enter Password") },
                    label = { Text("Enter Password") },
                    isError = passwordError.value,
                    trailingIcon = {
                        val image = if (passwordVisibility)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon(imageVector = image, "")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    onClick = {
                        if(!passwordError.value && !emailError.value) {
                            loginViewModel.login(
                                email = emailTextValue.value,
                                password = passwordTextValue.value
                            )
                        } else {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "Email or password must not be empty",
                                    "Ok"
                                )
                                navController.navigate(Screen.JobListing.route)
                            }
                        }
                    }) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(6.dp))
                when (loginViewState) {
                    is LoginViewState.Empty -> {
                    }
                    is LoginViewState.Loading -> {
                        CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
                    }
                    is LoginViewState.Success -> {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Login Successfully",
                                "Ok"
                            )
                            navController.navigate(Screen.JobListing.route)
                        }
                    }
                    is LoginViewState.Failure -> {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Login failed: ${(loginViewState as LoginViewState.Failure).e.message}",
                                "Ok"
                            )
                        }
                    }
                }
            }
        }
    }
}