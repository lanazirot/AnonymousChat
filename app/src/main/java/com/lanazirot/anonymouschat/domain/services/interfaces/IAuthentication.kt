package com.lanazirot.anonymouschat.domain.services.interfaces

import com.google.firebase.auth.FirebaseAuth

interface IAuthentication {
    fun getFirebaseAuth(): FirebaseAuth
}