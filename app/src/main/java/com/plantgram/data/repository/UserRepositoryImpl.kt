class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : UserRepository {

    private val usersCollection = firestore.collection("users")
    private val followsCollection = firestore.collection("follows")

    override suspend fun getUserProfile(userId: String): UserProfile? =
        withContext(Dispatchers.IO) {
            usersCollection.document(userId).get().await().toObject<UserProfile>()
        }

    override suspend fun updateUserProfile(profile: UserProfile) =
        withContext(Dispatchers.IO) {
            usersCollection.document(profile.userId).set(profile).await()
        }

    override suspend fun followUser(userId: String) = withContext(Dispatchers.IO) {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return@withContext

        followsCollection.document("${currentUserId}_${userId}").set(
            hashMapOf(
                "followerId" to currentUserId,
                "followingId" to userId,
                "timestamp" to FieldValue.serverTimestamp()
            )
        ).await()

        // Update counts
        firestore.runBatch { batch ->
            batch.update(usersCollection.document(userId), "followersCount", FieldValue.increment(1))
            batch.update(usersCollection.document(currentUserId), "followingCount", FieldValue.increment(1))
        }.await()
    }
}