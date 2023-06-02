package com.lanazirot.anonymouschat.ui.screens.login

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lanazirot.anonymouschat.domain.models.api.channel.ChannelMemberDTO
import com.lanazirot.anonymouschat.domain.models.api.location.LatLongDTO
import com.lanazirot.anonymouschat.domain.models.chat.Response
import com.lanazirot.anonymouschat.domain.models.chat.UserLogin
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IAuthenticationService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.ILocalStoreService
import com.lanazirot.anonymouschat.domain.services.interfaces.app.IStreamService
import com.lanazirot.anonymouschat.domain.services.interfaces.location.ILocationClient
import com.lanazirot.anonymouschat.ui.screens.login.states.LoginUIState
import com.lanazirot.anonymouschat.ui.screens.login.states.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authenticationService: IAuthenticationService,
    private val googleSignInClient: GoogleSignInClient,
    private val streamService: IStreamService,
    private val localStoreService: ILocalStoreService,
    private val locationClient: ILocationClient,
    ) : ViewModel() {
    private val _auth = authenticationService.getFirebaseAuth()
    private val _loading = MutableLiveData(false)
    private val _accessToken = MutableLiveData<String>()
    private val _serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _userState = MutableStateFlow(UserState())
    var userState: StateFlow<UserState> = _userState.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Waiting)
    var uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = LoginUIState.Waiting
            localStoreService.getStreamTokenAuth.collect { token ->
                _accessToken.value = token
            }
        }
    }

    fun updateUser(newUser: UserLogin) {
        _userState.value = _userState.value.copy(user = newUser)
    }

    //region Firebase
    fun signInWithCredentials() = viewModelScope.launch {
        try {
            if (_userState.value.user.email.isEmpty())
                setError("1")
            if (_userState.value.user.password.isEmpty())
                setError("2")
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(_userState.value.user.email).matches())
                setError("3")
            _uiState.value = LoginUIState.Loading
            _auth.signInWithEmailAndPassword(_userState.value.user.email, _userState.value.user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        connectUser(_userState.value.user.email)
                        _uiState.value = LoginUIState.Success
                    }
                }
                .addOnFailureListener { exception ->
                    throw exception
                }
        } catch (e: Exception) {
            _errorMessage.value = e.message ?: "Error al iniciar sesion"
            _uiState.value = LoginUIState.Error
        }
    }

    fun signInWithGoogle(credential: AuthCredential) {
        try {
            _uiState.value = LoginUIState.Loading
            _auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        connectUser(task.result?.user?.email ?: "")
                        _uiState.value = LoginUIState.Success
                    }
                }
                .addOnFailureListener { exception ->
                    throw exception
                }
        } catch (e: Exception) {
            _errorMessage.value = e.message ?: "Error al iniciar sesion"
            _uiState.value = LoginUIState.Error
        }
    }

    fun createUserWithCredentials() {
        try {
            _uiState.value = LoginUIState.Loading
            if(!_loading.value!!) {
                _loading.value = true
                _auth.createUserWithEmailAndPassword(_userState.value.user.email, _userState.value.user.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            updateUser(UserLogin(_userState.value.user.email, _userState.value.user.password))
                            signInWithCredentials()
                        }

                        _loading.value = false
                    }
                    .addOnFailureListener { exception ->
                        throw exception
                    }
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message ?: "Error al crear usuario"
            _uiState.value = LoginUIState.Error
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

    //region Location
    fun revealNewsChatsForCurrentUser() {
        val twoAndHalfMinutes = 30000
        locationClient.getLocationUpdates(twoAndHalfMinutes.toLong()).catch {
        }.onEach {

            /*
            * Entry point to check data.
            *
            * Here, every 30 seconds, we will check if the user has new room available.
            *
            * This is the most important part of the app, so we need to be careful with this. REALLY CAREFUL.
            *
            * */

            val latLong = LatLongDTO(it.latitude, it.longitude)
            val channelDTO = getCurrentUser()?.let { it1 -> ChannelMemberDTO(it1.id, latLong) }
            if(channelDTO != null) {
                streamService.revealNewsChatsForCurrentUser(channelDTO)
                Log.d("Location", "Location updated  $channelDTO")
            }

        }.launchIn(_serviceScope)
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

    fun setError(message: String) {
        _errorMessage.value = message
        _uiState.value = LoginUIState.Error
    }

    fun getCurrentUser() = streamService.getCurrentUser()
}