package com.plantgram.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.plantgram.ui.auth.AuthScreen
import com.plantgram.ui.feed.FeedScreen
import com.plantgram.ui.create.CreatePostScreen
import com.plantgram.ui.profile.ProfileScreen
import com.plantgram.ui.profile.UserProfileScreen
import com.plantgram.ui.plants.PlantManagementScreen
import com.plantgram.ui.plants.PlantDetailScreen

@Composable
fun PlantGramNavGraph(
    navController: NavHostController,
    startDestination: String = NavRoutes.Authentication.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.Authentication.route) {
            AuthScreen(
                onNavigateToFeed = {
                    navController.navigate(NavRoutes.Feed.route) {
                        popUpTo(NavRoutes.Authentication.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Feed.route) {
            FeedScreen(
                onNavigateToProfile = { userId ->
                    navController.navigate(NavRoutes.UserProfile.createRoute(userId))
                },
                onNavigateToPlantDetail = { plantId ->
                    navController.navigate(NavRoutes.PlantDetail.createRoute(plantId))
                }
            )
        }

        composable(NavRoutes.CreatePost.route) {
            CreatePostScreen(
                onPostCreated = {
                    navController.navigate(NavRoutes.Feed.route) {
                        popUpTo(NavRoutes.CreatePost.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavRoutes.Profile.route) {
            ProfileScreen(
                onNavigateToPlantManagement = {
                    navController.navigate(NavRoutes.PlantManagement.route)
                }
            )
        }

        composable(NavRoutes.PlantManagement.route) {
            PlantManagementScreen()
        }

        composable(
            route = NavRoutes.PlantDetail.route,
            arguments = listOf(navArgument("plantId") { type = NavType.StringType })
        ) { backStackEntry ->
            PlantDetailScreen(
                plantId = backStackEntry.arguments?.getString("plantId") ?: "",
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = NavRoutes.UserProfile.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            UserProfileScreen(
                userId = backStackEntry.arguments?.getString("userId") ?: "",
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}