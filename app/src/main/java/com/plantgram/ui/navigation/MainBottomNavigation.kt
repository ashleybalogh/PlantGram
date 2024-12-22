@Composable
fun MainBottomNavigation(
    navController: NavHostController,
    currentRoute: String?
) {
    NavigationBar {
        val items = listOf(
            BottomNavItem.Feed,
            BottomNavItem.CreatePost,
            BottomNavItem.PlantManagement,
            BottomNavItem.Profile
        )

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route.route,
                onClick = {
                    if (currentRoute != item.route.route) {
                        navController.navigate(item.route.route) {
                            popUpTo(navController.graph.startDestinationId) {
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
