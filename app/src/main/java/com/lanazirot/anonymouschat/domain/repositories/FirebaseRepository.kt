package com.lanazirot.anonymouschat.domain.repositories

import com.google.firebase.auth.FirebaseAuth
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthentication
import javax.inject.Inject

class FirebaseRepository @Inject constructor() : IAuthentication {
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}