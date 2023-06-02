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
class PreferencesViewModel @Inject constructor(
    private val localStoreService: ILocalStoreService
) : ViewModel() {
    private val _appLocale = MutableStateFlow("es")
    val appLocale:StateFlow<String> = _appLocale.asStateFlow()

    init {
        viewModelScope.launch {
            localStoreService.getAppLocale.collect { locale ->
                _appLocale.value = locale
            }
        }
    }

    fun setLocale(locale: String) {
        viewModelScope.launch {
            localStoreService.setAppLocale(locale)
            _appLocale.value = locale
        }
    }
}
