package com.coding.onboarding_presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.core.navigation.Route
import com.coding.core.domain.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
) : ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            authenticationService.signIn(email.value, password.value)
            openAndPopUp(Route.TIME_LINE, Route.LOGIN)
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Route.SIGN_UP, Route.LOGIN)
    }
}