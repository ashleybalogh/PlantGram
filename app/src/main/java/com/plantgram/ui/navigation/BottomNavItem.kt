// ui/navigation/BottomNavItem.kt
sealed class BottomNavItem(
    val route: NavRoutes,
    val title: String,
    val icon: ImageVector
) {
    object Feed : BottomNavItem(
        route = NavRoutes.Feed,
        title = "Feed",
        icon = Icons.Default.Home
    )

    object CreatePost : BottomNavItem(
        route = NavRoutes.CreatePost,
        title = "Create",
        icon = Icons.Default.Add
    )

    object Profile : BottomNavItem(
        route = NavRoutes.Profile,
        title = "Profile",
        icon = Icons.Default.Person
    )

    object PlantManagement : BottomNavItem(
        route = NavRoutes.PlantManagement,
        title = "Plants",
        icon = Icons.Default.Park
    )
}