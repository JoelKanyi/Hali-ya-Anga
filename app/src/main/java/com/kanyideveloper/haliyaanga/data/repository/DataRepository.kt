package com.kanyideveloper.haliyaanga.data.repository

import com.kanyideveloper.haliyaanga.data.remote.ApiService
import com.kanyideveloper.haliyaanga.data.response.WeatherResponse
import com.kanyideveloper.haliyaanga.util.Resource
import retrofit2.HttpException
import java.io.IOException

class DataRepository(private val api: ApiService) {

    suspend fun getWeatherData(location: String): Resource<WeatherResponse> {
        return try {
            return api.getWeatherData()
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
}