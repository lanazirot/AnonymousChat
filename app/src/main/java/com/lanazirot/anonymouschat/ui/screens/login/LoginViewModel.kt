package com.lanazirot.anonymouschat.ui.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.anonymouschat.domain.models.chat.UserLogin
import com.lanazirot.anonymouschat.domain.services.interfaces.IAuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authenticationService: IAuthenticationService,
    private val googleSignInClient: GoogleSignInClient,
) : ViewModel() {
    private val _auth = authenticationService.getFirebaseAuth()
    private val _loading = MutableLiveData(false)

    private val _userState = MutableStateFlow(UserState())
    var userState: StateFlow<UserState> = _userState.asStateFlow()

    fun updateUser(newUser: UserLogin) {
        _userState.value = _userState.value.copy(user = newUser)
    }

    fun signInWithCredentials(toHome: () -> Unit) = viewModelScope.launch {
        try {
            _auth.signInWithEmailAndPassword(_userState.value.user.email, _userState.value.user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toHome()
                        //TODO Guardar token global, generar token Stream
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
                            //TODO Crear usuario en Stream
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

    fun logout() {
        googleSignInClient.revokeAccess().addOnCompleteListener {
            Firebase.auth.signOut()
        }
    }
}