interface UserRepository {
    suspend fun getUserProfile(userId: String): UserProfile?
    suspend fun updateUserProfile(profile: UserProfile)
    suspend fun followUser(userId: String)
    suspend fun unfollowUser(userId: String)
    suspend fun getFollowers(userId: String): Flow<List<UserProfile>>
    suspend fun getFollowing(userId: String): Flow<List<UserProfile>>
    suspend fun searchUsers(query: String): Flow<List<UserProfile>>
}