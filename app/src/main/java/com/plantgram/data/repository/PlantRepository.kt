interface PlantRepository {
    suspend fun getPlants(userId: String): Flow<List<Plant>>
    suspend fun getPlant(plantId: String): Plant?
    suspend fun addPlant(plant: Plant): String
    suspend fun updatePlant(plant: Plant)
    suspend fun deletePlant(plantId: String)
    suspend fun updateWateringDate(plantId: String, date: Long)
    suspend fun addPlantImage(plantId: String, imageUri: Uri): String
}
