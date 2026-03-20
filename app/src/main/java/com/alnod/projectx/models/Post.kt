package com.alnod.projectx.models

data class Post(
    val id: Int,
    val user: User,
    val imageUrl: String,
    val caption: String,
    val isLiked: Boolean
)
