package com.coding.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.core.domain.service.AuthenticationService
import com.coding.timeline_domain.model.Post
import com.coding.timeline_domain.service.StoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val storeService: StoreService,
    private val authenticationService: AuthenticationService
): ViewModel() {

    val post = MutableStateFlow(DEFAULT_POST)

    fun savePost(popUpScreen: () -> Unit) {
        viewModelScope.launch {
            storeService.createPost(post.value)
        }
        popUpScreen()
    }

    fun updatePost(newText: String) {
        post.value = post.value.copy(text = newText)
    }
    companion object {
        private val DEFAULT_POST = Post()
    }
}