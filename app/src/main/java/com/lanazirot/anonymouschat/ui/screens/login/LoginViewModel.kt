package com.lanazirot.anonymouschat.ui.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.models.chat.UserLogin
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.ILocalStoreService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authenticationService: IAuthenticationService,
    private val googleSignInClient: GoogleSignInClient,
    private val streamService: IStreamService,
    private val localStoreService: ILocalStoreService
) : ViewModel() {
    private val _auth = authenticationService.getFirebaseAuth()
    private val _loading = MutableLiveData(false)
    private val _accessToken = MutableLiveData<String>()
    private val _userState = MutableStateFlow(UserState())
    var userState: StateFlow<UserState> = _userState.asStateFlow()

    init {
        viewModelScope.launch {
            localStoreService.getStreamTokenAuth.collect { token ->
                _accessToken.value = token
            }
        }
    }

    fun updateUser(newUser: UserLogin) {
        _userState.value = _userState.value.copy(user = newUser)
    }

    //region Firebase
    fun signInWithCredentials(toHome: () -> Unit) = viewModelScope.launch {
        try {
            _auth.signInWithEmailAndPassword(_userState.value.user.email, _userState.value.user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toHome()
                    }
                }
                .addOnFailureListener { exception ->
                    throw exception
                }
        } catch (e: Exception) {
            Log.d("LoginViewModel", e.toString())
        }
    }

    fun signInWithGoogle(credential: AuthCredential, toHome: () -> Unit) {
        try {
            _auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //TODO: Manipular viewmodel state

                        val token = connectUser(task.result?.user?.email ?: "")

                        toHome()
                    }
                }
                .addOnFailureListener { exception ->
                    throw exception
                }
        } catch (e: Exception) {
            Log.d("LoginViewModel", e.toString())
        }
    }

    fun createUserWithCredentials(email: String, password: String, toHome: () -> Unit) {
        try {
            if(!_loading.value!!) {
                _loading.value = true
                _auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            toHome()
                        }

                        _loading.value = false
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }
            }
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion

    //region Stream
    private fun connectUser(email: String) {
        val userResponse = streamService.getAnonymousUser(email)
        if (userResponse is Response.Success) {
            val user = userResponse.data
            if (user != null) {
                runBlocking {
                    val connectionResponse =
                        streamService.connectUser(
                            user,
                            tokenLocal = _accessToken.value ?: "",
                            lastAttempt = false
                        )

                    if (connectionResponse is Response.Success) {
                        viewModelScope.launch {
                            localStoreService.setStreamTokenAuth(connectionResponse.data!!) //Save local token
                        }
                    } else {
                        Log.d("StreamService", "User not connected")
                    }
                }
            }
        }
    }
    //endregion

    fun logout() {
        viewModelScope.launch {
            localStoreService.setStreamTokenAuth("")
        }

        googleSignInClient.revokeAccess().addOnCompleteListener {
            Firebase.auth.signOut()
        }
    }
}