package com.plantgram.ui.navigation

sealed class NavRoutes(val route: String) {
    object Feed : NavRoutes("feed")
    object CreatePost : NavRoutes("create_post")
    object Profile : NavRoutes("profile")
    object PlantManagement : NavRoutes("plant_management")
    object Authentication : NavRoutes("authentication")

    // Nested routes
    object PlantDetail : NavRoutes("plant_detail/{plantId}") {
        fun createRoute(plantId: String) = "plant_detail/$plantId"
    }
    object UserProfile : NavRoutes("user_profile/{userId}") {
        fun createRoute(userId: String) = "user_profile/$userId"
    }
}