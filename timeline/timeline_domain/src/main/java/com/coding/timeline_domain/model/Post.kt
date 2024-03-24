package com.coding.timeline_domain.model

import com.google.firebase.firestore.DocumentId

data class Post(
    @DocumentId val id: String = "",
    val text: String = "",
    val userId: String = ""
)