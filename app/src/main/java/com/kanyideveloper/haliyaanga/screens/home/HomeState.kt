package com.kanyideveloper.haliyaanga.screens.home

import com.kanyideveloper.haliyaanga.data.response.WeatherResponse

data class HomeState(
    val data: WeatherResponse? = null,
    val isLoading: Boolean = false
)
