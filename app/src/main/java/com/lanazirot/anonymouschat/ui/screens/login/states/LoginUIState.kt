package com.lanazirot.anonymouschat.ui.screens.login.states

sealed class LoginUIState {
    object Waiting : LoginUIState()
    object Loading : LoginUIState()
    object Success : LoginUIState()
    object Error : LoginUIState()
}