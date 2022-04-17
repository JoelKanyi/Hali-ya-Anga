package com.kanyideveloper.haliyaanga.data.response


import com.google.gson.annotations.SerializedName
import com.kanyideveloper.haliyaanga.model.Current
import com.kanyideveloper.haliyaanga.model.Forecast
import com.kanyideveloper.haliyaanga.model.Location

data class WeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("location")
    val location: Location
)