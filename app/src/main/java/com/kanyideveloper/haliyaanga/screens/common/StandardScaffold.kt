package com.kanyideveloper.haliyaanga.screens.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kanyideveloper.haliyaanga.model.BottomNavItem
import com.kanyideveloper.haliyaanga.ui.theme.MyBlue
import com.kanyideveloper.haliyaanga.ui.theme.PrimaryDark
import com.kanyideveloper.haliyaanga.ui.theme.SecondaryPrimaryDark

@Composable
fun StandardScaffold(
    navController: NavController,
    showBottomBar: Boolean = true,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Settings
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    Scaffold(
        backgroundColor = PrimaryDark,
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    backgroundColor = SecondaryPrimaryDark,
                    contentColor = Color.White,
                    modifier = Modifier.padding(12.dp).shadow(shape = RoundedCornerShape(10.dp), clip = true, elevation = 16.dp),
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = 9.sp
                                )
                            },
                            selectedContentColor = MyBlue,
                            unselectedContentColor = Color.LightGray,
                            alwaysShowLabel = true,
                            selected = currentDestination?.route?.contains(item.destination.route) == true,
                            onClick = {
                                navController.navigate(item.destination.route) {
                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}