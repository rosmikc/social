package com.coding.onboarding_presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.core.navigation.Route
import com.coding.core.domain.service.AuthenticationService
import com.coding.onboarding_domain.use_case.PasswordStrengthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("ERROR", throwable.message.orEmpty())
            },
            block = {
                authenticationService.signIn(email.value, password.value)
                openAndPopUp(Route.TIME_LINE, Route.LOGIN)
            }
        )
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(Route.SIGN_UP, Route.LOGIN)
    }
}