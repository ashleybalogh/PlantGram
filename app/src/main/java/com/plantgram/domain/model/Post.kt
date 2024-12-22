data class Plant(
    val id: String = "",
    val userId: String = "",
    val name: String = "",
    val species: String = "",
    val dateAdded: Long = System.currentTimeMillis(),
    val careLevel: String = "",
    val lightRequirement: String = "",
    val wateringSchedule: String = "",
    val lastWatered: Long? = null,
    val imageUrls: List<String> = emptyList(),
    val notes: String = ""
)