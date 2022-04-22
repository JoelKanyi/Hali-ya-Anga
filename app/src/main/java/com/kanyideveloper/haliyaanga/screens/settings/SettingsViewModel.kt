package com.kanyideveloper.haliyaanga.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.haliyaanga.data.local.Locations
import com.kanyideveloper.haliyaanga.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: DataRepository,
) : ViewModel() {

    private val _selectedLocation = mutableStateOf("")
    val selectedLocation: State<String> = _selectedLocation

    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    val locations = repository.getAllLocations()

    init {
        viewModelScope.launch {
            repository.currentlySelectedLocation.collect { loc ->
                _selectedLocation.value = loc.toString()
            }
        }
    }

    fun savedToSharedPrefs(locationName: String) {
        repository.saveToSharedPrefs(locationName)
    }

    fun setTextFieldValue(value: String) {
        _textFieldValue.value = value
    }

    fun insertLocation() {
        viewModelScope.launch {
            if (textFieldValue.value.isBlank()) {
                return@launch
            }
            repository.insertLocation(Locations(textFieldValue.value))
        }
    }
}