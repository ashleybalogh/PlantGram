interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): AuthResult<User>
    suspend fun signUpWithEmail(email: String, password: String, displayName: String): AuthResult<User>
    suspend fun signInWithGoogle(): AuthResult<User>
    suspend fun signOut()
    fun getCurrentUser(): User?
    fun isUserAuthenticated(): Boolean
}