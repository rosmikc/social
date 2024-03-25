package com.coding.timeline_domain.service

import android.net.Uri
import com.coding.timeline_domain.model.Post
import kotlinx.coroutines.flow.Flow

interface StoreService {
    val posts: Flow<List<Post>>
    suspend fun createPost(post: Post, uri: Uri?)
    suspend fun readPost(postId: String): Post?
}