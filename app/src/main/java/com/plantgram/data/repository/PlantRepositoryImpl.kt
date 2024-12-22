class PlantRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : PlantRepository {

    private val plantsCollection = firestore.collection("plants")

    override suspend fun getPlants(userId: String): Flow<List<Plant>> = callbackFlow {
        val subscription = plantsCollection
            .whereEqualTo("userId", userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val plants = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject<Plant>()
                } ?: emptyList()

                trySend(plants)
            }

        awaitClose { subscription.remove() }
    }

    override suspend fun addPlant(plant: Plant): String = withContext(Dispatchers.IO) {
        val docRef = plantsCollection.document()
        val plantWithId = plant.copy(id = docRef.id)
        docRef.set(plantWithId).await()
        return@withContext docRef.id
    }

    override suspend fun addPlantImage(plantId: String, imageUri: Uri): String =
        withContext(Dispatchers.IO) {
            val imagePath = "plants/$plantId/${UUID.randomUUID()}"
            val storageRef = storage.reference.child(imagePath)

            storageRef.putFile(imageUri).await()
            return@withContext storageRef.downloadUrl.await().toString()
        }
}