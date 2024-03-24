package com.coding.onboarding_presentation.splashscreen

import androidx.lifecycle.ViewModel
import com.coding.core.navigation.Route
import com.coding.core.domain.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val authenticationService: AuthenticationService
): ViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (authenticationService.hasUser()) openAndPopUp(Route.TIME_LINE, Route.SPLASH_SCREEN)
        else openAndPopUp(Route.LOGIN, Route.SPLASH_SCREEN)
    }
}