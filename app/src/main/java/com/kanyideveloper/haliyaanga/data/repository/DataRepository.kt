package com.kanyideveloper.haliyaanga.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.kanyideveloper.haliyaanga.data.local.Locations
import com.kanyideveloper.haliyaanga.data.local.LocationsDao
import com.kanyideveloper.haliyaanga.data.remote.ApiService
import com.kanyideveloper.haliyaanga.data.response.WeatherResponse
import com.kanyideveloper.haliyaanga.util.Constants.WEATHER_LOCATION
import com.kanyideveloper.haliyaanga.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class DataRepository(
    private val api: ApiService,
    private val dao: LocationsDao,
    private val sharedPreferences: SharedPreferences
) {

    val currentlySelectedLocation =
        MutableStateFlow(sharedPreferences.getString(WEATHER_LOCATION, "Nairobi"))

    suspend fun getWeatherData(location: String): Resource<WeatherResponse> {
        return try {
            Resource.Success(
                data = api.getWeatherData(location)
            )
        } catch (e: IOException) {
            return Resource.Error(
                message = "Oops! couldn't reach server, check your internet connection."
            )
        } catch (e: HttpException) {
            return Resource.Error(
                message = "Oops! something went wrong. Please try again"
            )
        }
    }

    suspend fun insertLocation(locations: Locations) {
        dao.insertLocation(locations)
    }

    fun getAllLocations(): LiveData<List<Locations>> {
        return dao.getAllLocations()
    }

    fun saveToSharedPrefs(locationName: String) {
        sharedPreferences.edit().putString(WEATHER_LOCATION, locationName).apply()
        currentlySelectedLocation.value = locationName
    }
}