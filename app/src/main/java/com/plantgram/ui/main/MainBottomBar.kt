package com.plantgram.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem(
            name = "Feed",
            route = NavRoutes.Feed.route,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "Create",
            route = NavRoutes.CreatePost.route,
            icon = Icons.Default.Add
        ),
        BottomNavItem(
            name = "Profile",
            route = NavRoutes.Profile.route,
            icon = Icons.Default.Person
        ),
        BottomNavItem(
            name = "Plants",
            route = NavRoutes.PlantManagement.route,
            icon = Icons.Default.LocalFlorist
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(text = item.name) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(NavRoutes.Feed.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)