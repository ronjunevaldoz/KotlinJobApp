package com.ronjunevaldoz.geoexam.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ronjunevaldoz.geoexam.data.LoginUseCases
import com.ronjunevaldoz.geoexam.ui.viewstate.LoginViewState
import com.ronjunevaldoz.geoexam.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val dataStoreManager: DataStoreManager,
    private val loginUseCases: LoginUseCases,
) : AndroidViewModel(application) {

    private val _loginViewState = MutableStateFlow<LoginViewState>(LoginViewState.Empty)
    val loginViewState = _loginViewState

    fun clearState() {
        _loginViewState.value = LoginViewState.Empty
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCases.login(
                email = email,
                password = password
            ).collectLatest { loginViewState ->
                _loginViewState.value = loginViewState
            }
        }
    }
}