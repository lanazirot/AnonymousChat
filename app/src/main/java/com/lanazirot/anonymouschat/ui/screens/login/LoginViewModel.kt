package com.lanazirot.anonymouschat.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.lanazirot.anonymouschat.domain.repositories.FirebaseRepository
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebase : FirebaseRepository
) : ViewModel() {
    private val auth = firebase.getFirebaseAuth()

    fun signInWithGoogle(credential: AuthCredential, toHome: () -> Unit) {
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toHome()
                    }
                }
                .addOnFailureListener() { exception ->
                    throw exception
                }
        } catch (e: Exception) {
            Log.d("LoginViewModel", e.toString())
        }
    }
}