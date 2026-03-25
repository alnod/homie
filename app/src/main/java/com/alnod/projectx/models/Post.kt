package com.alnod.projectx.models

data class Post(
    val id: String, // Changed to String for Firestore document IDs
    val user: User,
    val imageUrl: String,
    val caption: String,
    val isLiked: Boolean
)
