package com.coding.core.domain.service

import com.coding.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationService {
    val currentUser: Flow<User?>
    val currentUserId: String
    fun hasUser(): Boolean
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}