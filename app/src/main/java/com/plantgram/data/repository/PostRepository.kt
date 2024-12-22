interface PostRepository {
    suspend fun getFeed(userId: String, limit: Int = 20): Flow<List<Post>>
    suspend fun createPost(post: Post): String
    suspend fun getPost(postId: String): Post?
    suspend fun deletePost(postId: String)
    suspend fun likePost(postId: String)
    suspend fun unlikePost(postId: String)
    suspend fun addComment(postId: String, comment: Comment)
    suspend fun getComments(postId: String): Flow<List<Comment>>
}
