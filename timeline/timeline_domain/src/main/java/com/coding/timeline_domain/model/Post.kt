package com.coding.timeline_domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Post(
    @DocumentId val id: String = "",
    val text: String = "",
    val userId: String = "",
    val imageUrl: String? = null,
    val timestamp: Timestamp = Timestamp(Date())
)