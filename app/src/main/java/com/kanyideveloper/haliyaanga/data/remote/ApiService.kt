package com.kanyideveloper.haliyaanga.data.remote

import com.kanyideveloper.haliyaanga.BuildConfig.API_KEY
import com.kanyideveloper.haliyaanga.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("q") searchString: String,
        @Query("days") days: Int = 7,
        @Query("key") key: String = API_KEY,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
    ): WeatherResponse
}