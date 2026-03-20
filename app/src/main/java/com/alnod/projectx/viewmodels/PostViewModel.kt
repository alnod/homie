package com.alnod.projectx.viewmodels

import androidx.lifecycle.ViewModel
import com.alnod.projectx.data.samplePosts
import com.alnod.projectx.models.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PostViewModel : ViewModel() {

    private val _posts = MutableStateFlow(samplePosts)
    val posts: StateFlow<List<Post>> = _posts

    fun toggleLike(postId: Int) {
        _posts.value = _posts.value.map {
            if (it.id == postId) {
                it.copy(isLiked = !it.isLiked)
            } else it
        }
    }
}
