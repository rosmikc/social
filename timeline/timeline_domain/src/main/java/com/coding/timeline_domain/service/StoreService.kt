package com.coding.timeline_domain.service

import com.coding.timeline_domain.model.Post
import kotlinx.coroutines.flow.Flow

interface StoreService {
    val posts: Flow<List<Post>>
    suspend fun createPost(post: Post)
    suspend fun readPost(postId: String): Post?
}