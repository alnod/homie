package com.alnod.projectx.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.alnod.projectx.models.Post
import com.alnod.projectx.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

class FirestoreViewModel : ViewModel() {

    private val db: FirebaseFirestore? by lazy {
        try { FirebaseFirestore.getInstance() } catch (e: Exception) { null }
    }
    private val storage: FirebaseStorage? by lazy {
        try { FirebaseStorage.getInstance() } catch (e: Exception) { null }
    }
    private val auth: FirebaseAuth? by lazy {
        try { FirebaseAuth.getInstance() } catch (e: Exception) { null }
    }

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        db?.collection("posts")
            ?.orderBy("timestamp", Query.Direction.DESCENDING)
            ?.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    _error.value = e.localizedMessage
                    return@addSnapshotListener
                }

                val postList = snapshot?.documents?.mapNotNull { doc ->
                    val id = doc.id
                    val userId = doc.getString("userId") ?: ""
                    val userName = doc.getString("userName") ?: "Unknown"
                    val userImage = doc.getString("userImage") ?: ""
                    val imageUrl = doc.getString("imageUrl") ?: ""
                    val caption = doc.getString("caption") ?: ""
                    val isLiked = doc.get("likedBy")?.let { (it as? List<*>)?.contains(auth?.currentUser?.uid) } ?: false

                    Post(
                        id = id,
                        user = User(0, userName, userImage),
                        imageUrl = imageUrl,
                        caption = caption,
                        isLiked = isLiked
                    )
                } ?: emptyList()

                _posts.value = postList
            }
    }

    fun uploadPost(imageUri: Uri, caption: String, onSuccess: () -> Unit) {
        val currentUser = auth?.currentUser ?: return
        val currentStorage = storage ?: return
        val currentDb = db ?: return
        
        _isLoading.value = true

        val storageRef = currentStorage.reference.child("posts/${UUID.randomUUID()}.jpg")
        
        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { url ->
                    val postData = hashMapOf(
                        "userId" to currentUser.uid,
                        "userName" to (currentUser.displayName ?: currentUser.email?.split("@")?.get(0) ?: "User"),
                        "userImage" to (currentUser.photoUrl?.toString() ?: "https://ui-avatars.com/api/?name=${currentUser.email}&background=random"),
                        "imageUrl" to url.toString(),
                        "caption" to caption,
                        "timestamp" to System.currentTimeMillis(),
                        "likedBy" to listOf<String>()
                    )

                    currentDb.collection("posts").add(postData)
                        .addOnSuccessListener {
                            _isLoading.value = false
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            _isLoading.value = false
                            _error.value = e.localizedMessage
                        }
                }
            }
            .addOnFailureListener { e ->
                _isLoading.value = false
                _error.value = e.localizedMessage
            }
    }

    fun toggleLike(postIdString: String) {
        val currentUser = auth?.currentUser ?: return
        val currentDb = db ?: return
        val docRef = currentDb.collection("posts").document(postIdString)

        currentDb.runTransaction { transaction ->
            val snapshot = transaction.get(docRef)
            val likedBy = snapshot.get("likedBy") as? MutableList<String> ?: mutableListOf()

            if (likedBy.contains(currentUser.uid)) {
                likedBy.remove(currentUser.uid)
            } else {
                likedBy.add(currentUser.uid)
            }

            transaction.update(docRef, "likedBy", likedBy)
        }.addOnFailureListener { e ->
            _error.value = e.localizedMessage
        }
    }
}
