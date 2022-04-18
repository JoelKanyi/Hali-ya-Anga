package com.kanyideveloper.haliyaanga.model

import com.kanyideveloper.haliyaanga.R
import com.kanyideveloper.haliyaanga.screens.destinations.Destination
import com.kanyideveloper.haliyaanga.screens.destinations.HomeScreenDestination
import com.kanyideveloper.haliyaanga.screens.destinations.SearchScreenDestination
import com.kanyideveloper.haliyaanga.screens.destinations.SettingScreenDestination

sealed class BottomNavItem(var title: String, var icon: Int, var destination: Destination) {
    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )
    object Search: BottomNavItem(
        title = "Search",
        icon = R.drawable.ic_search,
        destination = SearchScreenDestination
    )
    object Settings: BottomNavItem(
        title = "Settings",
        icon = R.drawable.ic_settings,
        destination = SettingScreenDestination
    )
}