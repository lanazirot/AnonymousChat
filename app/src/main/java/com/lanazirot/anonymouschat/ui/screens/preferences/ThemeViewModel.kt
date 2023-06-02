package com.lanazirot.anonymouschat.ui.screens.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.anonymouschat.domain.services.interfaces.app.ILocalStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val localStoreService: ILocalStoreService
) : ViewModel() {
    private val _isDarkThemeEnabled = MutableStateFlow(true)
    val isDarkThemeEnabled: StateFlow<Boolean> = _isDarkThemeEnabled.asStateFlow()

    init {
        viewModelScope.launch {
            localStoreService.getDarkThemeState.collect { isDarkThemeEnabled ->
                _isDarkThemeEnabled.value = isDarkThemeEnabled
            }
        }
    }

    fun setDarkThemeEnabled(enabled: Boolean) {
        viewModelScope.launch {
            localStoreService.setDarkThemeState(enabled)
            _isDarkThemeEnabled.value = enabled
        }
    }
}