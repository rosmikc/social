package com.coding.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.core.domain.service.AuthenticationService
import com.coding.core.navigation.Route
import com.coding.timeline_domain.service.StoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeLineViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val storeService: StoreService
): ViewModel() {
    val posts = storeService.posts

    fun initialize(restartApp: (String) -> Unit) {
        viewModelScope.launch {
            authenticationService.currentUser.collect { user ->
                if (user == null) restartApp(Route.SPLASH_SCREEN)
            }
        }
    }
    fun onAddClick(openScreen: (String) -> Unit) {
        openScreen(Route.CREATE_POST)
    }

//    fun onNoteClick(openScreen: (String) -> Unit, note: Note) {
//        openScreen("$NOTE_SCREEN?$NOTE_ID=${note.id}")
//    }

    fun onSignOutClick() {
        viewModelScope.launch {
            authenticationService.signOut()
        }
    }

    fun onDeleteAccountClick() {
        viewModelScope.launch {
            authenticationService.deleteAccount()
        }
    }
}