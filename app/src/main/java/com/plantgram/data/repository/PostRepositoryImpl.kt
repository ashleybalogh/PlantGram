class PostRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : PostRepository {

    private val postsCollection = firestore.collection("posts")
    private val commentsCollection = firestore.collection("comments")

    override suspend fun getFeed(userId: String, limit: Int): Flow<List<Post>> = callbackFlow {
        val subscription = postsCollection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(limit.toLong())
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val posts = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject<Post>()
                } ?: emptyList()

                trySend(posts)
            }

        awaitClose { subscription.remove() }
    }

    override suspend fun createPost(post: Post): String = withContext(Dispatchers.IO) {
        val docRef = postsCollection.document()
        val postWithId = post.copy(id = docRef.id)
        docRef.set(postWithId).await()
        return@withContext docRef.id
    }
}