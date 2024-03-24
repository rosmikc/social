package com.coding.social

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.coding.core.navigation.Route
import com.coding.onboarding_presentation.login.LoginScreen
import com.coding.onboarding_presentation.signup.SignUpScreen
import com.coding.social.ui.theme.SocialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val appState = remember(navController) {
                    SocialAppState(navController)
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { padding ->
                    NavHost(
                        navController = navController,
                        modifier = Modifier.padding(padding),
                        startDestination = Route.SIGN_UP
                    ) {
                        composable(Route.LOGIN) {
                            LoginScreen(
//                                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                            )
                        }

                        composable(Route.SIGN_UP) {
                            SignUpScreen(
                                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
                            )
                        }

                        composable(Route.SPLASH_SCREEN) {
//                            SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//                        } )
                        }
                    }
                }
            }
        }
    }
}