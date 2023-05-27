package com.lanazirot.anonymouschat.domain.services.interfaces.app

import com.google.firebase.auth.FirebaseAuth
import io.getstream.chat.android.client.ChatClient

interface IAuthenticationService {
    fun getFirebaseAuth(): FirebaseAuth
    fun getChatClient() : ChatClient
}