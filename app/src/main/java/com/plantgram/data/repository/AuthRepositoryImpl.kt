class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthRepository {
    override suspend fun signInWithEmail(email: String, password: String): AuthResult<User> =
        withContext(Dispatchers.IO) {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                result.user?.let { firebaseUser ->
                    val user = getUserFromFirestore(firebaseUser.uid)
                    AuthResult.Success(user)
                } ?: AuthResult.Error("Authentication failed")
            } catch (e: Exception) {
                AuthResult.Error(e.message ?: "Authentication failed")
            }
        }

    override suspend fun signUpWithEmail(
        email: String,
        password: String,
        displayName: String
    ): AuthResult<User> = withContext(Dispatchers.IO) {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.let { firebaseUser ->
                val user = User(
                    id = firebaseUser.uid,
                    email = email,
                    displayName = displayName
                )
                createUserInFirestore(user)
                AuthResult.Success(user)
            } ?: AuthResult.Error("User creation failed")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "User creation failed")
        }
    }

    private suspend fun createUserInFirestore(user: User) {
        db.collection("users").document(user.id).set(user).await()
    }

    private suspend fun getUserFromFirestore(uid: String): User {
        return db.collection("users").document(uid).get().await().toObject<User>()
            ?: throw Exception("User not found")
    }
}