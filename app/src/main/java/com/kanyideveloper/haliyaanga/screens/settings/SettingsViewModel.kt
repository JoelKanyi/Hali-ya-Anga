package com.kanyideveloper.haliyaanga.screens.settings

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.haliyaanga.data.local.Locations
import com.kanyideveloper.haliyaanga.data.repository.DataRepository
import com.kanyideveloper.haliyaanga.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: DataRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    val currentLoc = sharedPreferences.getString(Constants.WEATHER_LOCATION, "Nairobi") ?: "Nairobi"
    fun savedToSharedPrefs(locationName: String){
        repository.saveToSharedPrefs(locationName)
    }
    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    fun setTextFieldValue(value: String){
        _textFieldValue.value = value
    }

    val locations = repository.getAllLocations()

    fun insertLocation(){
        viewModelScope.launch {
            if (textFieldValue.value.isBlank()){
                return@launch
            }
            repository.insertLocation(Locations(textFieldValue.value))
        }
    }
}