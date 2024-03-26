package com.coding.onboarding_presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.core.navigation.Route
import com.coding.core.domain.service.AuthenticationService
import com.coding.core.util.UiEvent
import com.coding.onboarding_domain.use_case.PasswordStrengthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val passwordStrengthValidator: PasswordStrengthValidator
) : ViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")
    val showValidationErrorDialog = MutableStateFlow(false)
    val validationErrors = MutableStateFlow("")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        val errors = passwordStrengthValidator(password.value, confirmPassword.value)
        if (errors.isEmpty()) {
            viewModelScope.launch {
                authenticationService.signUp(email.value, password.value)
                openAndPopUp(Route.TIME_LINE, Route.SIGN_UP)
            }
        } else {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.ShowAlertDialogue(errors.joinToString (", ")))
            }
        }

    }
}