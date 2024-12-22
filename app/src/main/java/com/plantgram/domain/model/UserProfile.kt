data class UserProfile(
    val userId: String = "",
    val displayName: String = "",
    val photoUrl: String? = null,
    val bio: String = "",
    val experienceLevel: String = "",
    val growingZone: String? = null,
    val plantCount: Int = 0,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val badges: List<Badge> = emptyList()
)

data class Badge(
    val id: String,
    val name: String,
    val iconUrl: String,
    val description: String,
    val dateEarned: Long
)