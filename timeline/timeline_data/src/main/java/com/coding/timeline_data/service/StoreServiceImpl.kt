package com.coding.timeline_data.service

import com.coding.core.domain.service.AuthenticationService
import com.coding.timeline_domain.model.Post
import com.coding.timeline_domain.service.StoreService
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StoreServiceImpl @Inject constructor(
    private val authenticationService: AuthenticationService
): StoreService {

    override val posts: Flow<List<Post>>
        get() =
            authenticationService.currentUser.flatMapLatest { post ->
                Firebase.firestore
                    .collection(POSTS_COLLECTION)
                    .whereEqualTo(USER_ID_FIELD, post?.id)
                    .dataObjects()
            }

    override suspend fun createPost(post: Post) {
        val noteWithUserId = post.copy(userId = authenticationService.currentUserId)
        Firebase.firestore
            .collection(POSTS_COLLECTION)
            .add(noteWithUserId).await()
    }

    override suspend fun readPost(postId: String): Post? {
        return Firebase.firestore
            .collection(POSTS_COLLECTION)
            .document(postId).get().await().toObject()
    }

    companion object {
        private const val USER_ID_FIELD = "userId"
        private const val POSTS_COLLECTION = "posts"
    }
}