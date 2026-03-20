package com.alnod.projectx.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth? by lazy {
        try {
            FirebaseAuth.getInstance()
        } catch (e: Exception) {
            null
        }
    }

    private val _user = MutableStateFlow<FirebaseUser?>(auth?.currentUser)
    val user: StateFlow<FirebaseUser?> = _user

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        auth?.addAuthStateListener { firebaseAuth ->
            _user.value = firebaseAuth.currentUser
        }
    }

    fun signUp(email: String, password: String) {
        val firebaseAuth = auth ?: return
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Email and password cannot be empty"
            return
        }

        _isLoading.value = true
        _error.value = null

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (!task.isSuccessful) {
                    _error.value = task.exception?.localizedMessage ?: "Sign up failed"
                }
            }
    }

    fun login(email: String, password: String) {
        val firebaseAuth = auth ?: return
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Email and password cannot be empty"
            return
        }

        _isLoading.value = true
        _error.value = null

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _isLoading.value = false
                if (!task.isSuccessful) {
                    _error.value = task.exception?.localizedMessage ?: "Login failed"
                }
            }
    }

    fun logout() {
        auth?.signOut()
    }

    fun clearError() {
        _error.value = null
    }
}
