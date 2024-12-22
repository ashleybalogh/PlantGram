data class User(
    val id: String,
    val email: String,
    val displayName: String,
    val photoUrl: String? = null,
    val experienceLevel: String? = null,
    val growingZone: String? = null
)