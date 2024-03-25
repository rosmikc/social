package com.coding.timeline_data.service

import android.net.Uri
import com.coding.core.domain.service.AuthenticationService
import com.coding.timeline_domain.model.Post
import com.coding.timeline_domain.service.StoreService
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.type.DateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import java.time.Instant
import java.util.Date
import javax.inject.Inject

class StoreServiceImpl @Inject constructor(
    private val authenticationService: AuthenticationService
): StoreService {

    val storageReference = Firebase.storage.reference
    override val posts: Flow<List<Post>>
        get() =
            authenticationService.currentUser.flatMapLatest { post ->
                Firebase.firestore
                    .collection(POSTS_COLLECTION)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(30)
                    .dataObjects()
            }

    override suspend fun createPost(post: Post, uri: Uri?) {
        uri?.let {
            val fileName = System.currentTimeMillis()
            val pathReference = storageReference.child("images/${fileName}.jpg")
            pathReference.putFile(uri).addOnSuccessListener { it ->
                pathReference.downloadUrl.addOnSuccessListener { firebaseUri ->
                    savePost(post, firebaseUri?.toString())
                }
            }
        }?:run{
            savePost(post)
        }
    }

    private fun savePost(post: Post, imageUrl: String? = null) {
        val timeStamp = Timestamp(Date())
        val noteWithUserId = post.copy(
            userId = authenticationService.currentUserId,
            imageUrl = imageUrl,
            timestamp = timeStamp
        )
        Firebase.firestore
            .collection(POSTS_COLLECTION)
            .add(noteWithUserId).addOnSuccessListener{

            }
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